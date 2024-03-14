package com.pbukki.creswave.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pbukki.creswave.entity.Comment;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(
        name = "Posts",
        description = "Table holding Posts Data"
)
@Data
@NoArgsConstructor @AllArgsConstructor
public class PostDto {
    @NotBlank(message = "Title cannot be blank")
    @Schema(name = "title", example = "Effective Java")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    @Schema(name = "content", example = "Cool Book for Learning Java")
    private String content;
    @JsonIgnore
    private Set<Comment> comments;
    @JsonIgnore
    private String createdBy;

}
