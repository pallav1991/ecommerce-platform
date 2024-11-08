package com.ecommerce.auth.services;

import com.ecommerce.auth.clients.UserServiceClient;
import com.ecommerce.auth.entities.AuthResponse;
import com.ecommerce.auth.entities.AuthUser;
import com.ecommerce.auth.entities.AuthenticationRequest;
import com.ecommerce.entities.User;
import com.ecommerce.jwtUtil.JWTTokenType;
import com.ecommerce.jwtUtil.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JWTUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    public AuthResponse createAuthenticationToken(AuthenticationRequest request) throws RuntimeException {
        // Get user details from User Service
        User user = userServiceClient.getUserByUsername(request.getUsername());

        if (user != null) {
            // Generate JWT token
            String accessToken = jwtUtil.generateToken(user.getId().toString(), user.getRoles(), JWTTokenType.ACCESS_TOKEN);
            String refreshToken = jwtUtil.generateToken(user.getId().toString(), user.getRoles(), JWTTokenType.REFRESH_TOKEN);
            return new AuthResponse(accessToken, refreshToken);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    public AuthResponse register(AuthUser request) {
        User registeredUser = userServiceClient.regieterUser(request);

        String accessToken = jwtUtil.generateToken(registeredUser.getId().toString(), registeredUser.getRoles(), JWTTokenType.ACCESS_TOKEN);
        String refreshToken = jwtUtil.generateToken(registeredUser.getId().toString(), registeredUser.getRoles(), JWTTokenType.REFRESH_TOKEN);

        return new AuthResponse(accessToken, refreshToken);
    }
}
