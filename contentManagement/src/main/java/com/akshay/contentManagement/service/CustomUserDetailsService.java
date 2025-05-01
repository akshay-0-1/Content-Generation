package com.akshay.contentManagement.service;

import com.akshay.contentManagement.entity.User;
import com.akshay.contentManagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        logger.debug("Authenticating user '{}'", username);

        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            // Create authorities list
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            // Add default ROLE_USER for all users
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            // If your User entity has a roles field, you would add them here
            // Example: user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

            logger.debug("User '{}' found with authorities: {}", username, authorities);

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        } catch (UsernameNotFoundException ex) {
            logger.warn("User '{}' not found", username);
            throw ex;
        } catch (Exception ex) {
            logger.error("Error during authentication for user '{}'", username, ex);
            throw new UsernameNotFoundException("Error during authentication", ex);
        }
    }
}