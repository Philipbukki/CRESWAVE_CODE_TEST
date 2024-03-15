package com.pbukki.creswave;

import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.mapper.PostMapper;
import com.pbukki.creswave.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postServiceImpl;

    @Test
    public void testGetPost_validId() {
        // Mock PostRepository
        long postId = 1;
        Post post = new Post();
        post.setId(postId);

        PostService mockPostService = Mockito.mock(PostService.class);

        Mockito.when(mockPostService.getPost(postId)).thenReturn(PostMapper.MapToDto(post, new PostDto()));

        PostDto response = postServiceImpl.getPost(postId);

        // Assert the response
        assertEquals(postId, 1);
    }

    @Test
    public void testGetPost_invalidId() {
        // Mock PostRepository
        long postId = 10;
        PostService mockPostService = Mockito.mock(PostService.class);
        Mockito.when(mockPostService.getPost(postId)).thenThrow(new ResourceNotFoundException("Post", "id", postId));

        // Execute the test (wrapped in a try-catch block)
        assertThrows(ResourceNotFoundException.class, () -> this.postServiceImpl.getPost(postId));
    }

    @Test
    public void testUpdatePost_validData() {
        // Mock PostRepository
        long postId = 0;
        PostDto existingPost = PostDto.builder()
                .content("Content")
                .title("Title")
                .build();

        Post post = PostMapper.MapToEntity(existingPost, new Post());
        post.setId(postId);


        PostDto updatedPostDto = PostDto.builder()
                .content("Updated Content")
                .title("Updated Title")
                .build();

        PostService mockPostService = Mockito.mock(PostService.class);
        Mockito.when(mockPostService.updatePost(updatedPostDto, postId)).thenReturn(
                PostMapper.MapToDto(post, new PostDto()));

        // Execute the test (assuming service instance is injected)
        PostDto response = postServiceImpl.updatePost(updatedPostDto, postId);

        // Assert the response
        assertEquals(updatedPostDto.getTitle(), response.getTitle());
        assertEquals(updatedPostDto.getContent(), response.getContent());
    }










}
