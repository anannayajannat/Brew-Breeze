package dev.jannat.BrewBreeze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow React to access this API
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "Welcome to Coffee Bakery Shop!";
    }
}
