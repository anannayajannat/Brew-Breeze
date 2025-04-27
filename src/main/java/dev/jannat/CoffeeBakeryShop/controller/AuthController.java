package dev.jannat.CoffeeBakeryShop.controller;

import dev.jannat.CoffeeBakeryShop.models.User;
import dev.jannat.CoffeeBakeryShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Show login form
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("content", "auth/login");
        return "layout";
    }

    // Handle login
    @PostMapping("/auth/login-form")
    public String login(String username, String password, HttpSession session, Model model)
    {
        // Unwrap the Optional and get the User object
        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get(); // Retrieve the user from the Optional

            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                if ("ADMIN".equals(user.getRole())) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/";
                }
            }
        }

        // If no user found or password mismatch
        model.addAttribute("error", "Invalid username or password.");
        model.addAttribute("title", "Login");
        model.addAttribute("content", "auth/login");
        return "layout";
    }

    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}

//
//    @GetMapping("/login")
//    public String loginForm(Model model) {
//        model.addAttribute("title", "Login");
//        model.addAttribute("content", "auth/login");
//        return "layout";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpSession session,
//                        Model model) {
//        User user = userService.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            session.setAttribute("user", user);
//            if ("ADMIN".equals(user.getRole())) {
//                return "redirect:/admin/dashboard";
//            } else {
//                return "redirect:/";
//            }
//        } else {
//            model.addAttribute("error", "Invalid username or password.");
//            model.addAttribute("title", "Login");
//            model.addAttribute("content", "auth/login");
//            return "layout";
//        }
//    }


