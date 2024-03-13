package com.pbukki.creswave.dto;

import com.pbukki.creswave.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PostDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private Set<Comment> comments;
}
