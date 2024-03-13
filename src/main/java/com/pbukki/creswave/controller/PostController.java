package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDto>> getPosts()
    {
        return ResponseEntity.ok(postService.listPosts());
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable long postId)
    {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PostMapping("")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable long postId)
    {
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }

}
