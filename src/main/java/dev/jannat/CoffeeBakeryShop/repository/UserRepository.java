package dev.jannat.CoffeeBakeryShop.repository;

import dev.jannat.CoffeeBakeryShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
