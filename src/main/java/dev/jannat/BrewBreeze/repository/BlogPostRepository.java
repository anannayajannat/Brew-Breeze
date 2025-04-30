package dev.jannat.BrewBreeze.repository;

import dev.jannat.BrewBreeze.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
