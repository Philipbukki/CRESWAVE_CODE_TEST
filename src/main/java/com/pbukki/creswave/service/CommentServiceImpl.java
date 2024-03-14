package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.CommentResponseDto;
import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.entity.Comment;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.BlogErrorException;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.exceptions.UnAuthorizedUserException;
import com.pbukki.creswave.mapper.CommentMapper;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.repository.CommentRepository;
import com.pbukki.creswave.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService
{
    private CommentRepository commentRepository;
    private PostService postService;
    private PostRepository postRepository;
    private AuthService authService;

    @Override
    public CommentDto addComment(long postId, CommentDto commentDto)
    {
        Post post= postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );

        Comment comment = CommentMapper.MapToEntity(commentDto, new Comment());
        comment.setPost(post);

        log.info("Saving comment to db");
        CommentDto commentDtoReturned = CommentMapper.MapToDto(commentRepository.save(comment), new CommentDto());
        log.info("Comment saved to the db");

        return commentDtoReturned;
    }

    @Override
    public CommentResponseDto getAllCommentsByPost(long postId, int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Comment> postPage = commentRepository.findAll(pageable);

        Page<CommentDto> comments =   postPage.map(comment -> CommentMapper.MapToDto(comment, new CommentDto()));

        return CommentResponseDto.build(comments.getContent(),comments.getNumber(),
                comments.getSize(),comments.getTotalElements(),comments.getTotalPages(),comments.isLast());

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId)
    {
        PostDto post = postService.getPost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!post.getComments().contains(comment)) {
            throw new BlogErrorException("Comment does not belong to the specified Post");
        }

        return CommentMapper.MapToDto(comment,new CommentDto());

    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto)
    {
        PostDto post = postService.getPost(postId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!Objects.equals(authService.getLoggedInUser().getUsername(), comment.getCreatedBy())){
            throw new UnAuthorizedUserException("You cannot edit a comment belonging to someone else");
        }

        if (!post.getComments().contains(comment)) {
            throw new BlogErrorException("Comment does not belong to the specified Post");
        }

        Comment updatedComment = CommentMapper.MapToEntity(commentDto, comment);


        return CommentMapper.MapToDto(commentRepository.save(updatedComment),new CommentDto());

    }

    @Override
    public String deleteComment(long postId, long commentId)
    {
        PostDto post = postService.getPost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!post.getComments().contains(comment)) {
            throw new BlogErrorException("Comment does not belong to the specified Post");
        }

        if(!Objects.equals(authService.getLoggedInUser().getUsername(), comment.getCreatedBy())){
            throw new UnAuthorizedUserException("You cannot edit a comment belonging to someone else");
        }

        post.getComments().remove(comment);
        commentRepository.delete(comment);

        return "Comment deleted successfully";
    }
}
