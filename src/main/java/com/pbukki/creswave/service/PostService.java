package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.dto.PostResponseDto;
import com.pbukki.creswave.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponseDto listPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPost(long postId);
    PostDto updatePost(PostDto postDto, long postId);
}
