package com.pbukki.creswave.service;

import com.pbukki.creswave.dto.LoginDto;
import com.pbukki.creswave.dto.ProfileUpdateDto;
import com.pbukki.creswave.dto.RegisterDto;
import com.pbukki.creswave.entity.Role;
import com.pbukki.creswave.entity.User;
import com.pbukki.creswave.exceptions.BlogErrorException;
import com.pbukki.creswave.repository.RoleRepository;
import com.pbukki.creswave.repository.UserRepository;
import com.pbukki.creswave.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           JwtTokenProvider jwtTokenProvider
    )
    {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

   @Override
    public User getLoggedInUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info(username);
        return userRepository.findByUsernameOrEmail(username,username).orElseThrow(
                ()-> new BlogErrorException("You have provided incorrect credentials")
        );
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
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
        roles.add(roleRepository.findByName("ROLE_USER").orElseThrow(
                ()-> new BlogErrorException("Add role with name ROLE_USER to proceed")
        ));
        if(user.getUsername().equalsIgnoreCase("admin")){
            roles.add(roleRepository.findByName("ROLE_ADMIN").orElseThrow(
                    ()-> new BlogErrorException("Add role with name ROLE_ADMIN to proceed")
            ));
        }
        user.setRoles(roles);
        userRepository.save(user);
        return "User saved successfully";

    }

    public boolean hasAnyRole(Authentication authentication, String... roles) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> Arrays.asList(roles).contains(authority.getAuthority()));
    }
    @Override
    public Role addRole(Role role)
    {
        String checkRole = role.getName().toLowerCase();
        if(roleRepository.existsByName(checkRole)){
            throw new BlogErrorException("Role with that name already exist");
        }
        return roleRepository.save(role);
    }

    @Override
    public String updateProfile(ProfileUpdateDto updateDto)
    {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        User userToUpdate = userRepository.findByUsernameOrEmail(currentUser,currentUser).orElseThrow(
                ()-> new BlogErrorException("You are not authorized to update this user profile")
        );

        log.info("Before updating user details");
        // Update user information
        userToUpdate.setName(updateDto.getName());
        log.info("After updating some user details user details");

        // Check if password is provided for update
        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            userToUpdate.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        log.info("After updating user details including possible password update");
        // Save updated user
        userRepository.save(userToUpdate);

        return "User profile updated successfully";
    }



}