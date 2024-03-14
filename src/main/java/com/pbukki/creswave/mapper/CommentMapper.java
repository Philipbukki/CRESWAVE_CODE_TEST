package com.pbukki.creswave.mapper;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.entity.Comment;

public class CommentMapper
{
    public static Comment MapToEntity(CommentDto commentDto, Comment comment)
    {
        comment.setBody(commentDto.getBody());
        comment.setPost(commentDto.getPost());
        return comment;

    }
    public static CommentDto MapToDto(Comment comment, CommentDto commentDto)
    {
        commentDto.setBody(comment.getBody());
        commentDto.setPost(comment.getPost());
        commentDto.setCreatedBy(comment.getCreatedBy());
        return commentDto;
    }
}
