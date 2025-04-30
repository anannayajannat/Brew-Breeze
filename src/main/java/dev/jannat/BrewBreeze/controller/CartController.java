package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.CartItem;
import dev.jannat.BrewBreeze.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping
    public List<CartItem> getCart(HttpSession session) {
        return cartService.getCartItems(session.getId());
    }

    @PostMapping("/add")
    public void addToCart(@RequestParam Long productId,
                          @RequestParam(defaultValue = "1") int quantity,
                          HttpSession session) {
        cartService.addToCart(session.getId(), productId, quantity);
    }

    @PostMapping("/update")
    public void updateCart(@RequestParam Long productId,
                           @RequestParam int quantity,
                           HttpSession session) {
        cartService.updateQuantity(session.getId(), productId, quantity);
    }

    @PostMapping("/remove")
    public void removeFromCart(@RequestParam Long productId,
                               HttpSession session) {
        cartService.removeFromCart(session.getId(), productId);
    }

    @PostMapping("/clear")
    public void clearCart(HttpSession session) {
        cartService.clearCart(session.getId());
    }
}
