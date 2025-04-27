package dev.jannat.CoffeeBakeryShop.service;

import dev.jannat.CoffeeBakeryShop.models.CartItem;
import dev.jannat.CoffeeBakeryShop.models.Product;
import dev.jannat.CoffeeBakeryShop.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public ShoppingCartService(CartItemRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    // Removed static from this method
    public List<CartItem> getCartItems(String sessionId) {
        return cartItemRepository.findBySessionId(sessionId);
    }

    public void addToCart(String sessionId, Long productId, int quantity) {
        Optional<Product> optionalProduct = productService.getProductById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get(); // Retrieve the product from the Optional

            CartItem existingItem = cartItemRepository.findBySessionIdAndProductId(sessionId, productId);
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cartItemRepository.save(existingItem);
            } else {
                CartItem newItem = new CartItem();
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                newItem.setSessionId(sessionId);
                cartItemRepository.save(newItem);
            }
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void removeFromCart(String sessionId, Long productId) {
        cartItemRepository.deleteBySessionIdAndProductId(sessionId, productId);
    }

    public void updateQuantity(String sessionId, Long productId, int quantity) {
        CartItem item = cartItemRepository.findBySessionIdAndProductId(sessionId, productId);
        if (item != null) {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
    }

    // Removed static from this method
    public void clearCart(String sessionId) {
        cartItemRepository.deleteBySessionId(sessionId);
    }

    public double getTotal(String sessionId) {
        List<CartItem> cartItems = getCartItems(sessionId);
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
