package dev.jannat.BrewBreeze.controller.admin;

import dev.jannat.BrewBreeze.models.Order;
import dev.jannat.BrewBreeze.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> viewOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/accept/{orderId}")
    public void acceptOrder(@PathVariable Long orderId) {
        orderService.acceptOrder(orderId);
    }

    @PostMapping("/deliver/{orderId}")
    public void deliverOrder(@PathVariable Long orderId) {
        orderService.markAsDelivered(orderId);
    }
}
