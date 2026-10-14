package com.educonnect.educonnectbackend.controller;

import com.educonnect.educonnectbackend.entity.User;
import com.educonnect.educonnectbackend.repository.UserRepository;
import com.educonnect.educonnectbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentProfileController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        String token = authHeader;
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }

        String username = token != null ? jwtUtil.extractUsername(token) : null;
        String name = token != null ? jwtUtil.extractName(token) : null;

        if (username != null) {
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Map<String, Object> profile = new HashMap<>();
                profile.put("username", user.getUsername());
                profile.put("name", user.getName() != null ? user.getName() : user.getUsername());
                profile.put("role", user.getRole());
                return ResponseEntity.ok(profile);
            }
        }

        if (name != null) {
            Map<String, Object> profile = new HashMap<>();
            profile.put("username", username);
            profile.put("name", name);
            profile.put("role", "STUDENT");
            return ResponseEntity.ok(profile);
        }

        // Default fallback if anonymous or expired
        return ResponseEntity.ok(Map.of(
                "username", "student",
                "name", "John Doe",
                "role", "STUDENT"
        ));
    }
}
