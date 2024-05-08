package com.pbukki.creswave.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pbukki.creswave.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Registration Details",
        description = "Contains User Registration Details Data"
)
public class RegisterDto
{
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, message = "name must be at least 8 characters")
    private String name;
    @Email(message = "Invalid email format")
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Username cannot be null")
    @Size(min = 5, message = "username must be at least 5 characters")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;
    @JsonIgnore
    private Set<Role> roles;
}