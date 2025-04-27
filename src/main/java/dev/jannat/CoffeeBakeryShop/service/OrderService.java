package dev.jannat.CoffeeBakeryShop.service;

import dev.jannat.CoffeeBakeryShop.models.CartItem;
import dev.jannat.CoffeeBakeryShop.models.Order;
import dev.jannat.CoffeeBakeryShop.repository.CartItemRepository;
import dev.jannat.CoffeeBakeryShop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;

    public OrderService(OrderRepository orderRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public void placeOrder(String sessionId, String name, String address) {
        List<CartItem> cartItems = cartItemRepository.findBySessionId(sessionId);
        if (!cartItems.isEmpty()) {
            Order order = new Order();
            order.setCustomerName(name);
            order.setCustomerAddress(address);
            order.setStatus("PENDING");
            order.setOrderTime(LocalDateTime.now());
            order.setItems(cartItems);
            orderRepository.save(order);

            cartItemRepository.deleteAll(cartItems); // Clear cart after order
        }
    }

    public void acceptOrder(Long orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus("ACCEPTED");
            orderRepository.save(order);
        });
    }

    public void markAsDelivered(Long orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus("DELIVERED");
            orderRepository.save(order);
        });
    }
}
