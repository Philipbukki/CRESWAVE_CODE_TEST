package com.pbukki.creswave.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pbukki.creswave.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    @NotBlank
    private String body;
    @NotNull
    @JsonIgnore
    private Post post;
}
