package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.CommentResponseDto;

import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDto addComment(long postId, CommentDto commentDto);
    CommentResponseDto getAllCommentsByPost(long postId, int pageNo, int pageSize, String sortBy, String sortDir);
    CommentDto getCommentById(long postId,long commentId);
    CommentDto updateComment(long postId,long commentId, CommentDto commentDto);
    String deleteComment(long postId,long commentId);

}
