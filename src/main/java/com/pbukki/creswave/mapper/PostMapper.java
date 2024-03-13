package com.pbukki.creswave.mapper;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.entity.Post;

public class PostMapper {
    public static Post MapToEntity(PostDto postDto, Post post)
    {
        post.setTitle(postDto.getTitle());
        postDto.setContent(postDto.getContent());
        return post;
    }
    public static PostDto MapToDto(Post post, PostDto postDto)
    {
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }

}
