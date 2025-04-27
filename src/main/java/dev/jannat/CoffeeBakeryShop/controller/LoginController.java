package dev.jannat.CoffeeBakeryShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.jannat.CoffeeBakeryShop.util.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // Define the LoginRequest class inside the controller if necessary, or import from another file
    public static class LoginRequest {
        private String username;
        private String password;

        // Getter and Setter methods
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Your login logic here (e.g., validate username, password, and generate JWT)

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Example logic: if valid, generate a JWT (just an example)
        if (username.equals("admin") && password.equals("password")) {
            String jwtToken = jwtTokenUtil.generateToken(username); // Using JwtTokenUtil to generate JWT
            return ResponseEntity.ok("JWT Token: " + jwtToken); // Return the JWT token
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
