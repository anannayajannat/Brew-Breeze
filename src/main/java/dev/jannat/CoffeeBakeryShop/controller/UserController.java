package dev.jannat.CoffeeBakeryShop.controller;

import dev.jannat.CoffeeBakeryShop.models.CartItem;
import dev.jannat.CoffeeBakeryShop.models.Order;
import dev.jannat.CoffeeBakeryShop.service.OrderService;
import dev.jannat.CoffeeBakeryShop.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ShoppingCartService cartService;
    private final OrderService orderService;

    public UserController(ShoppingCartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String checkoutPage(Model model, HttpSession session) {
        String sessionId = session.getId();
        List<CartItem> cartItems = cartService.getCartItems(sessionId); // Use cartService instance

        model.addAttribute("title", "Checkout");
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getTotal(sessionId));
        model.addAttribute("content", "user/checkout");
        return "layout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String customerName,
                             @RequestParam String deliveryAddress,
                             HttpSession session) {
        String sessionId = session.getId();
        List<CartItem> cartItems = cartService.getCartItems(sessionId); // Use cartService instance
        double totalAmount = cartService.getTotal(sessionId);

        Order order = new Order(sessionId, customerName, deliveryAddress, cartItems, totalAmount);
        orderService.saveOrder(order);

        cartService.clearCart(sessionId); // Clear cart after placing order
        return "redirect:/user/ordersuccess";
    }

    @GetMapping("/ordersuccess")
    public String orderSuccess(Model model) {
        model.addAttribute("title", "Order Success");
        model.addAttribute("message", "Your order has been placed successfully!");
        return "user/ordersuccess";  // create this simple view
    }

//    @Autowired
//    private EmailService emailService;

}
