package dev.jannat.BrewBreeze.service;

import dev.jannat.BrewBreeze.models.CartItem;
import dev.jannat.BrewBreeze.models.Order;
import dev.jannat.BrewBreeze.repository.CartItemRepository;
import dev.jannat.BrewBreeze.repository.OrderRepository;
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

            cartItemRepository.deleteAll(cartItems);
        }
    }

    public void updateOrderStatus(Long orderId, String status) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }

    public void acceptOrder(Long orderId) {
        updateOrderStatus(orderId, "ACCEPTED");
    }

    public void markAsDelivered(Long orderId) {
        updateOrderStatus(orderId, "DELIVERED");
    }
}
