package dev.jannat.CoffeeBakeryShop.repository;

import dev.jannat.CoffeeBakeryShop.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
