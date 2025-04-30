package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.User;
import dev.jannat.BrewBreeze.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/profile")
public class UserProfileController {

    private final UserRepository userRepository;

    public UserProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public User viewProfile(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
