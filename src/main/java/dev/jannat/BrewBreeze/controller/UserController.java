package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.CartItem;
import dev.jannat.BrewBreeze.models.Order;
import dev.jannat.BrewBreeze.service.OrderService;
import dev.jannat.BrewBreeze.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final ShoppingCartService cartService;
    private final OrderService orderService;

    public UserController(ShoppingCartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public List<CartItem> checkoutPage(@RequestParam String sessionId) {
        return cartService.getCartItems(sessionId);
    }

    @PostMapping("/checkout")
    public Order placeOrder(@RequestParam String sessionId,
                            @RequestParam String customerName,
                            @RequestParam String deliveryAddress) {
        List<CartItem> cartItems = cartService.getCartItems(sessionId);
        double totalAmount = cartService.getTotal(sessionId);

        Order order = new Order(sessionId, customerName, deliveryAddress, cartItems, totalAmount);
        orderService.saveOrder(order);
        cartService.clearCart(sessionId);
        return order;
    }
}
