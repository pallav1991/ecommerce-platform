package com.ecommerce.auth.clients;

import com.ecommerce.auth.entities.AuthUser;
import com.ecommerce.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/users/{username}")
    AuthUser getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/register")
    User regieterUser(@RequestBody User user);
}
