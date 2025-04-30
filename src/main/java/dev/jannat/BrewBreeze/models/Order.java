package dev.jannat.BrewBreeze.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private String customerName;
    private String customerAddress;
    private String status;  // PENDING, ACCEPTED, DELIVERED

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> items;

    private double totalAmount;
    private LocalDateTime orderTime;

    public Order() {}

    public Order(String sessionId, String customerName, String customerAddress, List<CartItem> items, double totalAmount) {
        this.sessionId = sessionId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = "PENDING";
        this.orderTime = LocalDateTime.now();
    }

    // Existing
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ADD these missing setters:

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
