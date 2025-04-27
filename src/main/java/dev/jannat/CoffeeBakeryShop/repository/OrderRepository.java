package dev.jannat.CoffeeBakeryShop.repository;

import dev.jannat.CoffeeBakeryShop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
