package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.BlogPost;
import dev.jannat.BrewBreeze.service.BlogPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogPostService blogPostService;

    public BlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> viewBlog() {
        return blogPostService.getAllBlogPosts();
    }
}
