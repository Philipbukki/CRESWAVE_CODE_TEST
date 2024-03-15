package com.pbukki.creswave.service;


import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthService {
    String login(LoginDto loginDto);
    User getLoggedInUser();
    String updateProfile(String userName, String password, RegisterDto updateDto);
    String register(RegisterDto registerDto);
    boolean hasAnyRole(Authentication authentication, String... roles);
}