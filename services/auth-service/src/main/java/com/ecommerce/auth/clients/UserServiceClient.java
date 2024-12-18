package com.ecommerce.auth.clients;

import com.ecommerce.auth.entities.AuthUser;
import com.ecommerce.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/users/{username}")
    AuthUser getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/api/users/register")
    User regieterUser(@RequestBody User user);

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id);
}
