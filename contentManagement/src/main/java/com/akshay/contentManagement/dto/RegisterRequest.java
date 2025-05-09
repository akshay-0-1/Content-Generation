package com.akshay.contentManagement.dto;

/**
 * Data Transfer Object for registration requests
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;

    // Default constructor
    public RegisterRequest() {
    }

    // Constructor with required fields
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor with all fields
    public RegisterRequest(String username, String password, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}