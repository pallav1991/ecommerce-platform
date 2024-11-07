package com.ecommerce.auth;

import com.ecommerce.entities.User;
import com.ecommerce.jwtUtil.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        // Get user details from User Service
        User user = userServiceClient.getUserByUsername(request.getUsername());

        // Verify password (password should be hashed and verified securely)
        if (user != null && user.getPassword().equals(request.getPassword())) {
            String jwt = jwtUtil.generateToken(user.getUsername(), user.getRoles());
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
