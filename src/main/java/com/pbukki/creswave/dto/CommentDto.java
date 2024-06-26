package com.pbukki.creswave.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pbukki.creswave.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Schema(
        name = "Comments",
        description = "Table holding Post Comments Data"
)
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @NotBlank(message = "Comment Body cannot be blank")
    private String body;
    @JsonIgnore
    private Post post;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
}
