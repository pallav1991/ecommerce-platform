package com.ecommerce.user.services;

import com.ecommerce.user.entities.User;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<com.ecommerce.entities.User> getAllUsers() {
        return userRepository.findAll().stream().map(user -> (com.ecommerce.entities.User) user).toList();
    }

    public Optional<com.ecommerce.entities.User> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> (com.ecommerce.entities.User) user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
