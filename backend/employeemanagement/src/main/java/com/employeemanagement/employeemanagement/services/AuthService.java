package com.employeemanagement.employeemanagement.services;

import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.AuthResponse;
import com.employeemanagement.employeemanagement.dto.LoginRequest;
import com.employeemanagement.employeemanagement.dto.RegisterRequest;
import com.employeemanagement.employeemanagement.entity.Role;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.repository.UserRepo;
import com.employeemanagement.employeemanagement.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User u = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .department(req.getDepartment())
                .designation(req.getDesignation())
                .address(req.getAddress())
                .role(Role.EMPLOYEE) // default role, allow override if needed
                .build();
        userRepository.save(u);
        String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());
        return new AuthResponse(token, u.getRole().name(), u.getName(), u.getEmail());
    }

    public AuthResponse login(LoginRequest req) {
        // authenticate (will throw if invalid)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        User u = userRepository.findByEmail(req.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());
        return new AuthResponse(token, u.getRole().name(), u.getName(), u.getEmail());
    }
}