package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.CommentResponseDto;
import com.pbukki.creswave.service.CommentService;
import com.pbukki.creswave.utilities.AppConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/posts/{postId}/comments", produces= MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable long postId,@Valid @RequestBody CommentDto commentDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(postId, commentDto));
    }

    @GetMapping
    public ResponseEntity<CommentResponseDto> getAllCommentsByPost(
            @PathVariable long postId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_FIELD, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return ResponseEntity.ok(commentService.getAllCommentsByPost(postId,pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId)
    {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long commentId,@Valid @RequestBody CommentDto commentDto)
    {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId)
    {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId));
    }

}
