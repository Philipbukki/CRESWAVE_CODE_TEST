package com.pbukki.creswave;

import com.pbukki.creswave.controller.PostController;
import com.pbukki.creswave.dto.PostDto;
import com.pbukki.creswave.dto.PostResponseDto;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.service.PostService;
import com.pbukki.creswave.utilities.AppConstants;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostControllerTest {

    @Test
    public void testGetPost_validId()
    {
        // Mock PostService
        long postId = 1;
        PostDto expectedPost = new PostDto();
        PostService mockPostService = Mockito.mock(PostService.class);
        Mockito.when(mockPostService.getPost(postId)).thenReturn(expectedPost);
        PostController controller = new PostController(mockPostService);

        // Execute the test
        ResponseEntity<PostDto> response = controller.getPost(postId);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPost, response.getBody());
    }


    @Test
    public void testGetPost_invalidId()
    {
        // Mock PostService
        long postId = 10;
        PostService mockPostService = Mockito.mock(PostService.class);
        Mockito.when(mockPostService.getPost(postId)).thenThrow(new ResourceNotFoundException("Post","id",10));
        PostController controller = new PostController(mockPostService);

        // Execute the test
        assertThrows(ResourceNotFoundException.class, () -> controller.getPost(postId));

    }



}
