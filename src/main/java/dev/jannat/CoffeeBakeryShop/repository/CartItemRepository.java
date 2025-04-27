package dev.jannat.CoffeeBakeryShop.repository;


import dev.jannat.CoffeeBakeryShop.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findBySessionId(String sessionId);

    CartItem findBySessionIdAndProductId(String sessionId, Long productId);

    void deleteBySessionId(String sessionId);

    void deleteBySessionIdAndProductId(String sessionId, Long productId);
}