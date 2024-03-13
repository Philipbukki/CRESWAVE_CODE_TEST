package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.entity.Comment;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.BlogErrorException;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.mapper.CommentMapper;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService
{
    private CommentRepository commentRepository;
    private PostService postService;

    @Override
    public CommentDto addComment(long postId, CommentDto commentDto)
    {
        PostDto post= postService.getPost(postId);
        Comment comment = CommentMapper.MapToEntity(commentDto, new Comment());
        comment.setPost(PostMapper.MapToEntity(post, new Post()));

        return CommentMapper.MapToDto(commentRepository.save(comment), new CommentDto());

    }

    @Override
    public Set<CommentDto> getAllCommentsByPost(long postId)
    {
        PostDto post = postService.getPost(postId);
        Set<Comment> comments = post.getComments();

        return comments.stream()
                .map(comment -> CommentMapper.MapToDto(comment, new CommentDto()))
                .collect(Collectors.toSet());
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

        if (!post.getComments().contains(comment)) {
            throw new BlogErrorException("Comment does not belong to the specified Post");
        }

        return CommentMapper.MapToDto(commentRepository.save(comment),new CommentDto());

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

        post.getComments().remove(comment);
        commentRepository.delete(comment);

        return "Comment deleted successfully";
    }
}
