package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.dto.PostResponseDto;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.exceptions.UnAuthorizedUserException;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private AuthService authService;

    //Creates a new blog post record
    @Override
    @Transactional
    public PostDto createPost(PostDto postDto)
    {
        Post post = PostMapper.MapToEntity(postDto, new Post());
        log.info("post assigned");
        postRepository.save(post);
        log.info("Post saved");
        return PostMapper.MapToDto(post, new PostDto());
    }

    //Returns a list of paginated blog posts
    @Override
    public PostResponseDto listPosts(int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postPage = postRepository.findAll(pageable);

        Page<PostDto> posts = postPage.map(post -> PostMapper.MapToDto(post, new PostDto()));

        return PostResponseDto.build(posts.getContent(), posts.getNumber(),
                posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    //Retrieves post by id
    @Override
    public PostDto getPost(long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );
        return PostMapper.MapToDto(post, new PostDto());
    }

    //Retrieves a post from the db using post id and updates the retrieved post
    @Override
    public PostDto updatePost(PostDto updatedPost, long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (post.getCreatedBy().equals(currentUser) || isAdmin) {
            // Update post logic
            log.info("updating post");
            Post savedPost = postRepository.save(PostMapper.MapToEntity(updatedPost, post));
            log.info("post updated");
            return PostMapper.MapToDto(savedPost, new PostDto());

        } else {
            throw new UnAuthorizedUserException("You can't delete a post belonging to someone else");
        }

    }

    public String deletePost(long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authService.getLoggedInUser().getUsername();

        if (post.getCreatedBy().equals(currentUser) ||authService.hasAnyRole(authentication, "ROLE_ADMIN")) {

            log.info("deleting  post");
            postRepository.delete(post);
            log.info("Updating post");
            return "Blog post deleted successfully";

        } else {
            throw new UnAuthorizedUserException("You can't delete a post belonging to someone else");
        }

    }

    //Filters blog posts by their title or content
    @Override
    public List<PostDto> findByTitleOrContent(String title, String content) {
        List<Post> posts = postRepository.findByTitleContainingOrContentContaining(title, content);

        return posts.stream().map(post -> PostMapper.MapToDto(post, new PostDto()))
                .collect(Collectors.toList());

    }


}
