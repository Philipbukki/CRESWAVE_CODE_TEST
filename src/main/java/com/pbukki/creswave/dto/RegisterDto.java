package com.pbukki.creswave.dto;


import com.pbukki.creswave.entity.Role;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;

}