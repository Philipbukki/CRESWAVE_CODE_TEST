package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name="REST Endpoints for Authentication",
        description = "Endpoints for Registering and Login"
)
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = {"login","signin"})
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto)
    {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping (value={"register","signup"})
    ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto)
    {
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}