package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.CommentResponseDto;
import com.pbukki.creswave.entity.Comment;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.BlogErrorException;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.exceptions.UnAuthorizedUserException;
import com.pbukki.creswave.mapper.CommentMapper;
import com.pbukki.creswave.repository.CommentRepository;
import com.pbukki.creswave.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private AuthService authService;

    private Post getPostByIdOrThrow(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );
    }

    private Comment getCommentByIdOrThrow(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId)
        );
    }

    private void validateCommentBelongsToPost(Comment comment, Post post) {
        if (comment.getPost().getId() != post.getId()) {
            throw new BlogErrorException("Comment does not belong to the specified Post");
        }
    }

    @Override
    public CommentDto addComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = CommentMapper.MapToEntity(commentDto, new Comment());
        comment.setPost(post);

        log.info("Saving comment to db");
        CommentDto commentDtoReturned = CommentMapper.MapToDto(commentRepository.save(comment), new CommentDto());
        log.info("Comment saved to the db");

        return commentDtoReturned;
    }

    @Override
    public CommentResponseDto getAllCommentsByPost(long postId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Comment> postPage = commentRepository.findAllByPostId(postId, pageable);

        Page<CommentDto> comments = postPage.map(comment -> CommentMapper.MapToDto(comment, new CommentDto()));

        return CommentResponseDto.build(comments.getContent(), comments.getNumber(),
                comments.getSize(), comments.getTotalElements(), comments.getTotalPages(), comments.isLast());

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = getPostByIdOrThrow(postId);

        Comment comment = getCommentByIdOrThrow(commentId);
        // Check if the retrieved comment belongs to the specified post
        validateCommentBelongsToPost(comment, post);
        return CommentMapper.MapToDto(comment, new CommentDto());
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto)
    {
        Post post = getPostByIdOrThrow(postId);
        Comment comment = getCommentByIdOrThrow(commentId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        log.warn("creator "+comment.getCreatedBy());
        log.info("current "+currentUser);
        log.warn(" "+isAdmin);

        if (comment.getCreatedBy().equals(currentUser) || isAdmin)
        {
            validateCommentBelongsToPost(comment, post);
            log.info("validations complete");
            Comment updatedComment = CommentMapper.MapToEntity(commentDto, comment);
            log.info("mapping complete");
            return CommentMapper.MapToDto(commentRepository.save(updatedComment), new CommentDto());

        } else {
            throw new UnAuthorizedUserException("You can't update a comment belonging to someone else");
        }

    }

    @Override
    public String deleteComment(long postId, long commentId)
    {
        Post post = getPostByIdOrThrow(postId);
        Comment comment = getCommentByIdOrThrow(commentId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (comment.getCreatedBy().equals(currentUser) || isAdmin)
        {
            validateCommentBelongsToPost(comment, post);
            commentRepository.delete(comment);
            return "Comment deleted successfully";

        } else {
            throw new UnAuthorizedUserException("You can't delete a comment belonging to someone else");
        }

    }
}
