package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = PostMapper.MapToEntity(postDto, new Post());
        return PostMapper.MapToDto(post, new PostDto());
    }

    @Override
    public List<PostDto> listPosts() {

        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post->PostMapper.MapToDto(post, new PostDto())).
                collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );
        return PostMapper.MapToDto(post, new PostDto());
    }

    @Override
    public PostDto updatePost(PostDto updatedPost, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );

        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());

        Post savedPost = postRepository.save(post);

        return  PostMapper.MapToDto(savedPost, new PostDto());

    }
}
