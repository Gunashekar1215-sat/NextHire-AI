package com.nexthire.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexthire.backend.model.User;
import com.nexthire.backend.repository.UserRepository;

@Service
public class UserService {
    public String login(String email, String password) {

    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {
        return "User Not Found";
    }

    if (user.getPassword().equals(password)) {
        return "Login Successful";
    }

    return "Invalid Password";
}

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}