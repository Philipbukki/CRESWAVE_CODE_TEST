package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.CommentDto;
import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDto addComment(long postId, CommentDto commentDto);
    Set<CommentDto> getAllCommentsByPost(long postId);
    CommentDto getCommentById(long postId,long commentId);
    CommentDto updateComment(long postId,long commentId, CommentDto commentDto);
    String deleteComment(long postId,long commentId);

}
