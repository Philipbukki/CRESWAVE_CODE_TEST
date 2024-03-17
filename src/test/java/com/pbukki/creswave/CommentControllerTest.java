package com.pbukki.creswave;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbukki.creswave.controller.CommentController;
import com.pbukki.creswave.dto.CommentDto;
import com.pbukki.creswave.dto.CommentResponseDto;
import com.pbukki.creswave.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testAddComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Test comment");

        when(commentService.addComment(anyLong(), any(CommentDto.class))).thenReturn(commentDto);

        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(commentDto)))
                .andExpect(status().isCreated());
    }


    @Test
    public void testGetCommentById() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Test comment");

        when(commentService.getCommentById(anyLong(), anyLong())).thenReturn(commentDto);

        mockMvc.perform(get("/api/v1/posts/1/comments/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody("Updated comment");

        when(commentService.updateComment(anyLong(), anyLong(), any(CommentDto.class))).thenReturn(commentDto);

        mockMvc.perform(put("/api/v1/posts/1/comments/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(commentDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteComment() throws Exception {
        when(commentService.deleteComment(anyLong(), anyLong())).thenReturn("Comment deleted successfully");

        mockMvc.perform(delete("/api/v1/posts/1/comments/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Comment deleted successfully"));
    }
}
