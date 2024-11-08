package com.ecommerce.auth.controller;

import com.ecommerce.auth.entities.AuthResponse;
import com.ecommerce.auth.entities.AuthUser;
import com.ecommerce.auth.entities.AuthenticationRequest;
import com.ecommerce.auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            AuthResponse token = authService.createAuthenticationToken(request);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthUser request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
