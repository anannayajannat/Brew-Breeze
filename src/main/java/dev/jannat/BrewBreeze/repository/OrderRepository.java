package dev.jannat.BrewBreeze.repository;

import dev.jannat.BrewBreeze.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
