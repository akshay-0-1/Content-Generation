package com.akshay.contentManagement.dto;

import java.util.List;

/**
 * Data Transfer Object for authentication responses
 */
public class AuthResponse {
    private Long userId;
    private String username;
    private String token;
    private List<String> roles;
    private boolean success;
    private String message;

    // Default constructor
    public AuthResponse() {
    }

    // Constructor with all fields
    public AuthResponse(Long userId, String username, String token, List<String> roles, boolean success, String message) {
        this.userId = userId;
        this.username = username;
        this.token = token;
        this.roles = roles;
        this.success = success;
        this.message = message;
    }

    // Success response factory method
    public static AuthResponse success(Long userId, String username, String token, List<String> roles, String message) {
        return new AuthResponse(userId, username, token, roles, true, message);
    }

    // Error response factory method
    public static AuthResponse error(String message) {
        return new AuthResponse(null, null, null, null, false, message);
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}