package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.Order;
import dev.jannat.BrewBreeze.repository.OrderRepository;
import dev.jannat.BrewBreeze.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartService cartService;

    @PostMapping("/place")
    public Order placeOrder(@RequestParam String customerName,
                            @RequestParam String customerAddress,
                            HttpSession session) {
        Order order = cartService.checkout(session.getId(), customerName, customerAddress);
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
