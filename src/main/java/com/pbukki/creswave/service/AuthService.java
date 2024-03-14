package com.pbukki.creswave.service;


import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.entity.User;

public interface AuthService {
    String login(LoginDto loginDto);
    User getLoggedInUser();
    String register(RegisterDto registerDto);
}