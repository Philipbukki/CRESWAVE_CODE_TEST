package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.CommentResponseDto;
import com.pbukki.creswave.service.CommentService;
import com.pbukki.creswave.utilities.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name="Endpoints for Post Comments",
        description = "REST Endpoints for create, read,fetch, update and delete operations Posts Comments"
)
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = "/api/v1/posts/{postId}/comments", produces= MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @Operation(
            summary = "Add Post Comment",
            description = "Endpoint for Adding comment to a Post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "HTTP STATUS BAD_REQUEST"
    )
    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable long postId,@Valid @RequestBody CommentDto commentDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(postId, commentDto));
    }
    @Operation(
            summary = "List Comments",
            description = "Endpoint for Comments on a Post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
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

    @Operation(
            summary = "Get a Single Post Comment",
            description = "Endpoint for Getting a Single Post Comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @ApiResponse(
            responseCode = "404",
            description = "HTTP STATUS NOT_FOUND"
    )

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId)
    {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @Operation(
            summary = "Update Post Comment",
            description = "Endpoint for Updating a Comment on a Post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "HTTP STATUS BAD_REQUEST"
    )
    @ApiResponse(
            responseCode = "404",
            description = "HTTP STATUS NOT_FOUND"
    )

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long commentId,@Valid @RequestBody CommentDto commentDto)
    {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }
    @Operation(
            summary = "Delete Post Comment",
            description = "Endpoint for Deleting a Post comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "HTTP STATUS BAD_REQUEST"
    )
    @ApiResponse(
            responseCode = "404",
            description = "HTTP STATUS NOT_FOUND"
    )
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId)
    {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId));
    }

}
