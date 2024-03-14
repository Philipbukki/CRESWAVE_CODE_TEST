package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.dto.PostResponseDto;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    //Creates a new blog post record
    @Override
    @Transactional
    public PostDto createPost(PostDto postDto)
    {
        Post post = PostMapper.MapToEntity(postDto, new Post());
        postRepository.save(post);
        return PostMapper.MapToDto(post, new PostDto());
    }

    //Returns a list of paginated blog posts
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

    //Retrieves post by id
    @Override
    public PostDto getPost(long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );
        return PostMapper.MapToDto(post, new PostDto());
    }

    //Retrieves a post from the db using post id and updates the retrieved post
    @Override
    public PostDto updatePost(PostDto updatedPost, long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );

      log.info("updating post");
      Post savedPost = postRepository.save(PostMapper.MapToEntity(updatedPost, post));
      log.info("post updated");

        return  PostMapper.MapToDto(savedPost, new PostDto());

    }

    //Filters blog posts by their title or content
    @Override
    public List<PostDto> findByTitleOrContent(String title, String content)
    {
        List<Post> posts = postRepository.findByTitleContainingOrContentContaining(title,content);

        return posts.stream().map(post->PostMapper.MapToDto(post,new PostDto()))
                .collect(Collectors.toList());

    }

}
