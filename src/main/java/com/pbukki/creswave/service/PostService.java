package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.entity.Post;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> listPosts();
    PostDto getPost(long postId);
    PostDto updatePost(PostDto postDto, long postId);
}
