package com.pbukki.creswave;

import com.pbukki.creswave.controller.AuthController;
import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    @Test
    public void testRegister_validUser() {
        // Mock AuthService
        RegisterDto registerDto = RegisterDto.builder()
                .username("username")
                .email("email@example.com")
                .password("password")
                .build();

        String expectedResponse = "User Registered Successfully";
        AuthService mockAuthService = Mockito.mock(AuthService.class);
        Mockito.when(mockAuthService.register(registerDto)).thenReturn(expectedResponse);

        AuthController controller = new AuthController(mockAuthService);

        // Execute the test
        ResponseEntity<String> response = controller.register(registerDto);

        // Assert the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }


    @Test
    public void testUpdateProfile_validData() {
        // Mock AuthService
        String username = "test_user";
        String password = "test_password";

        RegisterDto updateDto = RegisterDto.builder()
                .username("username")
                .email(null)
                .password(null)
                .build();

        String expectedResponse = "Profile Updated Successfully";
        AuthService mockAuthService = Mockito.mock(AuthService.class);
        Mockito.when(mockAuthService.updateProfile(username, password, updateDto)).thenReturn(expectedResponse);


    }
}
