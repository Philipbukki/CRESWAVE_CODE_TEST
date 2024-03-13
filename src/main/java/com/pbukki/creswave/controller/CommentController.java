package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;


@RestController
@RequestMapping("/api/posts/{postId}/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable long postId,@Valid @RequestBody CommentDto commentDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(postId, commentDto));
    }

    @GetMapping
    public ResponseEntity<Set<CommentDto>> getAllCommentsByPost(@PathVariable long postId)
    {
        return ResponseEntity.ok(commentService.getAllCommentsByPost(postId));
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
