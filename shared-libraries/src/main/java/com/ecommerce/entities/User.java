package com.ecommerce.entities;


import java.util.Set;

public interface User {

    Long getId();

    String getUsername();

    Set<String> getRoles();

    String getPassword();

    void setPassword(String password);

    void setRoles(Set<String> roles);

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);
}
