package dev.jannat.CoffeeBakeryShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("title", "Home");
        model.addAttribute("message", "Welcome to our Coffee Bakery Shop!");
        return "index";
    }
}