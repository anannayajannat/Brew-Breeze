package dev.jannat.BrewBreeze.controller.admin;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    @GetMapping
    public String dashboard() {
        return "Admin Dashboard Data (replace with real summary later)";
    }

    @GetMapping("/inventory")
    public String inventory() {
        return "Inventory Management Data (replace with inventory summary)";
    }

    @GetMapping("/orders")
    public String orders() {
        return "Order Management Data (replace with orders summary)";
    }
}
