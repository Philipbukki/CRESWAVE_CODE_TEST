package com.pbukki.creswave.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pbukki.creswave.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(
        name = "Posts",
        description = "Table holding Posts Data"
)
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PostDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @NotBlank(message = "Title cannot be blank")
    @Schema(name = "title", example = "Effective Java")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    @Schema(name = "content", example = "Cool Book for Learning Java")
    private String content;
    @JsonIgnore
    private Set<Comment> comments;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

}
