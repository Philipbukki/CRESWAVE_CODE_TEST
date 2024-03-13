package com.pbukki.creswave.service;


import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}