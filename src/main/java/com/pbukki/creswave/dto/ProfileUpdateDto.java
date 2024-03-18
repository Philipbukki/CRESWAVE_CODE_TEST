package com.pbukki.creswave.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class ProfileUpdateDto {
    @NotNull(message = "Name cannot be null")
    private String name;
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;
}
