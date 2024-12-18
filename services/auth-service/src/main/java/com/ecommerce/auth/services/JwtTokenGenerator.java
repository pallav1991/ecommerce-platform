package com.ecommerce.auth.services;

import com.ecommerce.jwtUtil.JWTTokenType;
import com.ecommerce.jwtUtil.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class JwtTokenGenerator {

    private final JWTUtil jwtUtil;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities());
        return jwtUtil.generateToken(claims,null, JWTTokenType.ACCESS_TOKEN);
    }

    public String extractUsername(String token) {
        return jwtUtil.extractUsername(token);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        if (!jwtUtil.isTokenExpired(token)){
            if (jwtUtil.extractUsername(token).equals(userDetails.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean validateToken(String token) {
        if (!jwtUtil.isTokenExpired(token)){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            if (jwtUtil.extractUsername(token).equals(userDetails.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
