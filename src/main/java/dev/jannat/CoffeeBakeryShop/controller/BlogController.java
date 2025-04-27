package dev.jannat.CoffeeBakeryShop.controller;

import dev.jannat.CoffeeBakeryShop.models.BlogPost;
import dev.jannat.CoffeeBakeryShop.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final BlogPostService blogPostService;

    public BlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String viewBlog(Model model) {
        model.addAttribute("title", "Blog");
        model.addAttribute("posts", blogPostService.getAllBlogPosts());
        model.addAttribute("content", "user/blog");
        return "layout";
    }
}
