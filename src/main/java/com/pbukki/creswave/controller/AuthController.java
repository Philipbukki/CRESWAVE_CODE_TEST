package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name="REST Endpoints for Authentication",
        description = "Endpoints for Registering and Login"
)
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Login/SignIn User",
            description = "Endpoint for User Login"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )

    @ApiResponse(
            responseCode = "400",
            description = "HTTP STATUS BAD_REQUEST"
    )

    @PostMapping(value = {"login","signIn"})
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto)
    {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Register/SignUp User",
            description = "Endpoint for User Registration"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    @ApiResponse(
            responseCode = "400",
            description = "HTTP STATUS BAD_REQUEST"
    )
    @PostMapping (value={"register","signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto)
    {
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update User Profile",
            description = "Endpoint for Updating User Profile"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "HTTP STATUS BAD_REQUEST"
    )
    @ApiResponse(
            responseCode = "404",
            description = "HTTP STATUS NOT_FOUND"
    )
    @PutMapping("/update_profile")
    public ResponseEntity<String> updateProfile(
            @RequestParam String userName, @RequestParam String password,
            @Valid @RequestBody RegisterDto updateDto)
    {
            return ResponseEntity
                    .ok()
                    .body(authService.updateProfile(userName, password, updateDto));

    }

}