package dev.jannat.CoffeeBakeryShop.controller.admin;

import dev.jannat.CoffeeBakeryShop.service.OrderService;
import dev.jannat.CoffeeBakeryShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

    private final OrderService orderService;
    private final ProductService productService;

    // 🛠 Manually write constructor:
    public AdminDashboardController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalOrders", orderService.getAllOrders().size());
        model.addAttribute("pendingOrders", orderService.getAllOrders().stream()
                .filter(order -> "Pending".equalsIgnoreCase(order.getStatus()))
                .count());

        model.addAttribute("totalProducts", productService.getAllProducts().size());

        model.addAttribute("title", "Admin Dashboard");
        model.addAttribute("content", "admin/dashboard");
        return "layout";
    }
}
