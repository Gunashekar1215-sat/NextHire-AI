package com.nexthire.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexthire.backend.model.LoginRequest;
import com.nexthire.backend.model.User;
import com.nexthire.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/login-test")
public String loginTest() {
    return userService.login(
            "guna@gmail.com",
            "123456"
    );
}
    @PostMapping("/login")
public String login(@RequestBody LoginRequest request) {

    return userService.login(
            request.getEmail(),
            request.getPassword()
    );
}
    @GetMapping("/test")
public User testUser() {

    User user = new User();
    user.setName("Guna");
    user.setEmail("guna@gmail.com");
    user.setPassword("123456");

    return userService.saveUser(user);
}

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}