package com.educonnect.educonnectbackend.controller;

import com.educonnect.educonnectbackend.dto.LoginRequest;
import com.educonnect.educonnectbackend.dto.LoginResponse;
import com.educonnect.educonnectbackend.entity.User;
import com.educonnect.educonnectbackend.repository.UserRepository;
import com.educonnect.educonnectbackend.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        User user = optionalUser.get();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole(), user.getName());

        LoginResponse response = new LoginResponse(
                token,
                user.getUsername(),
                user.getName(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username is already taken"));
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("STUDENT");
        }

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole(), savedUser.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(
                token,
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getRole()
        ));
    }
}
