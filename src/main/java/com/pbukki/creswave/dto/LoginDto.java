package com.pbukki.creswave.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Schema(
        name = "Login Details",
        description = "Contains User Login Details Data"
)
public class LoginDto {
    @NotBlank(message = "username or email cannot be blank")
    @NotNull(message = "username or email cannot be null")
    @Schema(name = "usernameOrEmail", example = "johndoe@gmail.com")
    private String usernameOrEmail;
    @NotBlank(message = "password or email cannot be blank")
    @NotNull(message = "password or email cannot be null")
    @Schema(name = "password", example = "abcd1234")
    private String password;
}