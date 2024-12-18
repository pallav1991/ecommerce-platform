package com.ecommerce.auth.services;

import com.ecommerce.auth.clients.UserServiceClient;
import com.ecommerce.auth.entities.AuthResponse;
import com.ecommerce.auth.entities.AuthUser;
import com.ecommerce.auth.entities.AuthenticationRequest;
import com.ecommerce.entities.User;
import com.ecommerce.jwtUtil.JWTTokenType;
import com.ecommerce.jwtUtil.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final UserServiceClient userServiceClient;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse createAuthenticationToken(AuthenticationRequest request) throws RuntimeException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            // Get user details from User Service
            User user = userServiceClient.getUserByUsername(request.getUsername());

            if (user != null) {
                // Generate JWT token
                String accessToken = jwtUtil.generateToken(user.getId().toString(), user.getRoles(), JWTTokenType.ACCESS_TOKEN);
                String refreshToken = jwtUtil.generateToken(user.getId().toString(), user.getRoles(), JWTTokenType.REFRESH_TOKEN);
                return new AuthResponse(accessToken, refreshToken);
            }
            else {
                throw new UsernameNotFoundException("User not found");
            }
        }
        else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public Boolean validateToken(String token) {
        if (jwtTokenGenerator.validateToken(token)) {
            return true;
        }
        return false;
    }

    public Boolean register(AuthUser request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(hashedPassword);
        User registeredUser = userServiceClient.regieterUser(request);
        if (registeredUser == null || registeredUser.getId() == null) {
            return false;
        }

        return true;
    }
}
