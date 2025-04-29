package com.akshay.contentManagement.controller;

import com.akshay.contentManagement.entity.User;
import com.akshay.contentManagement.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser, HttpSession session) {
        Optional<User> user = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getId());
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User newUser) {
        // Check if username is already taken
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Basic password length validation
        if (newUser.getPassword() == null || newUser.getPassword().length() < 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 6 characters long");
        }

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }
}
