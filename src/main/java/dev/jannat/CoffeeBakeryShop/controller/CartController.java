package dev.jannat.CoffeeBakeryShop.controller;



import dev.jannat.CoffeeBakeryShop.models.CartItem;
import dev.jannat.CoffeeBakeryShop.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")

public class CartController {
    private final ShoppingCartService cartService;

    public CartController(ShoppingCartService cartService) {

        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        String sessionId = session.getId();

//        model.addAttribute("cartItems", cartService.getCartItems(sessionId));
//        model.addAttribute("total", cartService.getTotal(sessionId));
//        return "cart/view";
        List<CartItem> cartItems = cartService.getCartItems(sessionId);

        model.addAttribute("title", "Cart");
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getTotal(sessionId));
        model.addAttribute("cartItemCount", cartItems.size());
        model.addAttribute("content", "cart/view");

        return "layout";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        cartService.addToCart(session.getId(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        cartService.updateQuantity(session.getId(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        cartService.removeFromCart(session.getId(), productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        cartService.clearCart(session.getId());
        return "redirect:/cart";
    }
}