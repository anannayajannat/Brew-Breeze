package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.User;
import dev.jannat.BrewBreeze.security.JwtTokenProvider;
import dev.jannat.BrewBreeze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User user) {
        String token = userService.authenticate(user);
        return Map.of("token", token);
    }
}
