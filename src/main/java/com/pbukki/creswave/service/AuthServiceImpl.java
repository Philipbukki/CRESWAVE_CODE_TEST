package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.entity.Role;
import com.pbukki.creswave.entity.User;
import com.pbukki.creswave.exceptions.BlogErrorException;
import com.pbukki.creswave.exceptions.ResourceNotFoundException;
import com.pbukki.creswave.repository.RoleRepository;
import com.pbukki.creswave.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository)
    {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

   @Override
    public User getLoggedInUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsernameOrEmail(username,username).get();
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogErrorException("User with that username exists");

        }
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogErrorException("User with that email already exists");

        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return "User saved successfully";

    }

    public boolean hasAnyRole(Authentication authentication, String... roles) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> Arrays.asList(roles).contains(authority.getAuthority()));
    }


    @Override
    public String updateProfile(String userName, String password, RegisterDto updateDto) {
        // Get the authenticated user
        User authenticatedUser = getLoggedInUser();

        User userToUpdate = userRepository
                .findByUsernameOrEmail(authenticatedUser.getUsername(),authenticatedUser.getEmail()).orElseThrow(
                        ()->new ResourceNotFoundException("User","username or email",authenticatedUser.getId())
                );

        log.info(authenticatedUser.getUsername());

        log.error("Before mess*****************************************");

        // Check if the provided username matches the authenticated user's username
        if (!authenticatedUser.getUsername().equals(userName)) {
            throw new BlogErrorException("You are not authorized to update this profile");
        }

        log.error("Before mess*****************************************");

        // Check if the provided password matches the authenticated user's password
        if (!passwordEncoder.matches(password, authenticatedUser.getPassword())) {
            throw new BlogErrorException("You have entered incorrect username or password");
        }

        log.info("Before updating user details");
        // Update user information
        userToUpdate.setName(updateDto.getName());
        userToUpdate.setUsername(updateDto.getUsername());
        userToUpdate.setEmail(updateDto.getEmail());

        log.info("After updating some user details user details");

        // Check if password is provided for update
        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            userToUpdate.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }

        log.info("After updating user details including possible password update");

        // Save updated user
        userRepository.save(authenticatedUser);

        return "User profile updated successfully";
    }

}