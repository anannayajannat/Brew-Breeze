package dev.jannat.CoffeeBakeryShop.controller.admin;

import dev.jannat.CoffeeBakeryShop.models.Order;
import dev.jannat.CoffeeBakeryShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String viewOrders(Model model) {
        List<Order> allOrders = orderService.getAllOrders();
        model.addAttribute("orders", allOrders);

        long newOrdersCount = allOrders.stream()
                .filter(order -> "Pending".equalsIgnoreCase(order.getStatus()))
                .count();
        model.addAttribute("newOrdersCount", newOrdersCount);

        model.addAttribute("title", "Manage Orders");
        model.addAttribute("content", "admin/manageOrders");
        return "layout";
    }

    @PostMapping("/accept")
    public String acceptOrder(@RequestParam("orderId") Long orderId) {
        orderService.acceptOrder(orderId);
        return "redirect:/admin/orders";
    }

    @PostMapping("/deliver")
    public String deliverOrder(@RequestParam("orderId") Long orderId) {
        orderService.markAsDelivered(orderId);
        return "redirect:/admin/orders";
    }
}
