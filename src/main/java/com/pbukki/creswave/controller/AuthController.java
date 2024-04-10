package com.pbukki.creswave.controller;

import com.pbukki.creswave.dto.*;
import com.pbukki.creswave.entity.Role;
import com.pbukki.creswave.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirement(name = "Bearer Authentication")
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            )
    })

    @PostMapping(value = {"/login", "/signIn"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @Operation(
            summary = "Register/SignUp User",
            description = "Endpoint for User Registration"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            )
    })
    @PostMapping (value={"register","signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto)
    {
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Rest User Password",
            description = "Endpoint for Resetting User Password"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))

            )
    })
    @PutMapping ("reset_password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto)
    {
        return  new ResponseEntity<>(authService.resetPassword(passwordResetDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Update User Profile",
            description = "Endpoint for Updating User Profile"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST"
            )
    })

    @PutMapping("/update_profile")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody ProfileUpdateDto updateDto)
    {
            return ResponseEntity
                    .ok()
                    .body(authService.updateProfile(updateDto));

    }

    @Operation(
            summary = "Add User Role",
            description = "Endpoint for Adding Available System User Roles"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD_REQUEST",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })

    @PostMapping("/add_role")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.addRole(role));
    }

}