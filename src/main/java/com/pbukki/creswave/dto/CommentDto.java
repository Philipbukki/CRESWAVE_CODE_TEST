package com.pbukki.creswave.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pbukki.creswave.entity.Post;
import com.pbukki.creswave.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(
        name = "Comments",
        description = "Table holding Post Comments Data"
)
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommentDto {

    @JsonIgnore
    private long id;
    @NotBlank(message = "Comment Body cannot be blank")
    private String body;
    @JsonIgnore
    private Post post;
    private String createdBy;
}
