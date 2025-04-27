package dev.jannat.CoffeeBakeryShop.service;

import dev.jannat.CoffeeBakeryShop.models.BlogPost;
import dev.jannat.CoffeeBakeryShop.repository.BlogPostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public void addPost(BlogPost post) {
        blogPostRepository.save(post);
    }

    public void deletePostById(Long id) {
        blogPostRepository.deleteById(id);
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }
}
