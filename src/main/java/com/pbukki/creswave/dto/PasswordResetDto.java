package com.pbukki.creswave.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordResetDto
{
    @Schema(name = "oldPassword", example = "abcd1234")
    private String oldPassword;
    @Schema(name = "newPassword", example = "test16")
    @NotNull(message = "newPassword cannot be null")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String newPassword;
    @Schema(name = "confirmPassword", example = "test16")
    @NotNull(message = "Confirm Password cannot be null")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String confirmPassword;
}
