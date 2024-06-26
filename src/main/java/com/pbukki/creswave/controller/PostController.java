package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.ErrorResponseDto;
import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.dto.PostResponseDto;
import com.pbukki.creswave.service.PostService;
import com.pbukki.creswave.utilities.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name="Blog Posts Endpoints",
        description = "Endpoints for Posts CRUD Operations"
)
public class PostController {
    private final PostService postService;
    @Operation(
            summary = "List Posts",
            description = "Endpoint for Listing Posts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("")
    public ResponseEntity<PostResponseDto> getPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_FIELD, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return ResponseEntity.ok(postService.listPosts(pageNo,pageSize,sortBy,sortDir));
    }
    @Operation(
            summary = "Retrieve Post",
            description = "Endpoint for Getting a Single Post"
    )
   @ApiResponses({
           @ApiResponse(
                   responseCode = "200",
                   description = "HTTP STATUS OK"
           ),
           @ApiResponse(
                   responseCode = "404",
                   description = "HTTP STATUS NOT_FOUND",
                   content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
           )
   })

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable long postId)
    {
        return ResponseEntity.ok(postService.getPost(postId));
    }
    @Operation(
            summary = "Add Post",
            description = "Endpoint for Creating a Post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    @PostMapping("")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
    @Operation(
            summary = "Update Post",
            description = "Endpoint for Updating an Existing Post"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT_FOUND",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "HTTP STATUS UNAUTHORIZED",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            )
    })

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable long postId) throws InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }

    @Operation(
            summary = "Delete Post",
            description = "Endpoint for Deleting a Blog Post"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT_FOUND",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "HTTP STATUS UNAUTHORIZED",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            )
    })

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) throws InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(postService.deletePost(postId));
    }
    @Operation(
            summary = "Search Posts",
            description = "Endpoint for Fetching Posts using Titles and Content"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPostsByTitleOrContent(

            @RequestParam(required = false) String queryParam)
    {

        return  ResponseEntity.ok(postService.findByTitleOrContent(queryParam));

    }

}
