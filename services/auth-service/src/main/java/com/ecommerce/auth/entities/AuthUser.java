package com.ecommerce.auth.entities;

import com.ecommerce.entities.User;
import lombok.Setter;

import java.util.Set;

public class AuthUser implements User {
    @Setter
    private Long id;
    private String username;
    private Set<String> roles;
    private String password;
    private String email;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Set<String> getRoles() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
