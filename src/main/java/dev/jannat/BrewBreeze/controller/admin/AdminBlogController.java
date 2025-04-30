package dev.jannat.BrewBreeze.controller.admin;

import dev.jannat.BrewBreeze.models.BlogPost;
import dev.jannat.BrewBreeze.service.BlogPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/blog")
public class AdminBlogController {

    private final BlogPostService blogPostService;

    public AdminBlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> manageBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @PostMapping("/add")
    public BlogPost addPost(@RequestBody BlogPost blogPost) {
        return blogPostService.addPost(blogPost);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBlogPost(@PathVariable Long id) {
        blogPostService.deletePostById(id);
    }
}
