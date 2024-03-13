package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.dto.PostResponseDto;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto)
    {
        Post post = PostMapper.MapToEntity(postDto, new Post());
        return PostMapper.MapToDto(post, new PostDto());
    }

    @Override
    public PostResponseDto listPosts(int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort  sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(pageable);

        Page<PostDto> posts = postPage.map(post->PostMapper.MapToDto(post, new PostDto()));

        return PostResponseDto.build(posts.getContent(),posts.getNumber(),
                posts.getSize(),posts.getTotalElements(),posts.getTotalPages(),posts.isLast());
    }

    @Override
    public PostDto getPost(long postId)
    {
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
