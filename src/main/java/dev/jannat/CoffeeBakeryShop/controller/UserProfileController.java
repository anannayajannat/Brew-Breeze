package dev.jannat.CoffeeBakeryShop.controller;

import dev.jannat.CoffeeBakeryShop.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {

    @GetMapping
    public String viewProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("title", "Your Profile");
        model.addAttribute("content", "user/profile");
        return "layout";
    }
}
