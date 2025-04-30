package dev.jannat.BrewBreeze.service;

import dev.jannat.BrewBreeze.models.CartItem;
import dev.jannat.BrewBreeze.models.Order;
import dev.jannat.BrewBreeze.repository.CartItemRepository;
import dev.jannat.BrewBreeze.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getCartItems(String sessionId) {
        return cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getSessionId().equals(sessionId))
                .toList();
    }

    public void addToCart(String sessionId, Long productId, int quantity) {
        CartItem item = new CartItem();
        item.setSessionId(sessionId);
        item.setProduct(productRepository.findById(productId).orElseThrow());
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void updateQuantity(String sessionId, Long productId, int quantity) {
        CartItem item = cartItemRepository.findAll()
                .stream()
                .filter(c -> c.getSessionId().equals(sessionId) && c.getProduct().getId().equals(productId))
                .findFirst().orElseThrow();
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeFromCart(String sessionId, Long productId) {
        CartItem item = cartItemRepository.findAll()
                .stream()
                .filter(c -> c.getSessionId().equals(sessionId) && c.getProduct().getId().equals(productId))
                .findFirst().orElseThrow();
        cartItemRepository.delete(item);
    }

    public void clearCart(String sessionId) {
        List<CartItem> cartItems = getCartItems(sessionId);
        cartItemRepository.deleteAll(cartItems);
    }

    public double getTotal(String sessionId) {
        return getCartItems(sessionId).stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public Order checkout(String sessionId, String customerName, String customerAddress) {
        List<CartItem> items = getCartItems(sessionId);
        double totalAmount = getTotal(sessionId);

        Order order = new Order(sessionId, customerName, customerAddress, items, totalAmount);
        clearCart(sessionId);
        return order;
    }
}
