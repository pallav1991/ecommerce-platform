package com.ecommerce.auth;

import com.ecommerce.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/user-service/users/{username}")
    User getUserByUsername(@PathVariable("username") String username);
}
