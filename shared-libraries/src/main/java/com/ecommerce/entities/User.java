package com.ecommerce.entities;


import java.util.Set;

public interface User {

    String getUsername();

    Set<String> getRoles();

    String getPassword();

    void setPassword(String password);

    void setRoles(Set<String> roles);

    void setUsername(String username);
}
