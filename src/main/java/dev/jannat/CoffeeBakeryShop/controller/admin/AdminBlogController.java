package dev.jannat.CoffeeBakeryShop.controller.admin;

import dev.jannat.CoffeeBakeryShop.models.BlogPost;
import dev.jannat.CoffeeBakeryShop.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

    private final BlogPostService blogPostService;

    public AdminBlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String manageBlogPosts(Model model) {
        List<BlogPost> posts = blogPostService.getAllBlogPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("title", "Manage Blog");
        model.addAttribute("content", "admin/manageBlogPosts");
        return "admin/layout";
    }

    @GetMapping("/add")
    public String showAddBlogForm(Model model) {
        model.addAttribute("post", new BlogPost());
        model.addAttribute("title", "Add Blog Post");
        model.addAttribute("content", "admin/addBlogPost");
        return "admin/layout";
    }

    @PostMapping("/save")
    public String saveBlogPost(@ModelAttribute BlogPost blogPost) {
        blogPostService.addPost(blogPost);
        return "redirect:/admin/blog";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlogPost(@PathVariable Long id) {
        blogPostService.deletePostById(id);
        return "redirect:/admin/blog";
    }
}







//    @GetMapping("/edit/{id}")
//    public String editBlogPost(@PathVariable Long id, Model model) {
//        model.addAttribute("blogPost", blogPostService.getBlogPostById(id));
//        return "admin/blog/edit";
//    }
//
//    @PostMapping("/update")
//    public String updateBlogPost(@ModelAttribute BlogPost blogPost) {
//        blogPostService.updateBlogPost(blogPost);
//        return "redirect:/admin/blog";
//    }






//    @GetMapping("/add")
//    public String addBlogForm(Model model) {
//        model.addAttribute("title", "Add Blog Post");
//        model.addAttribute("content", "admin/addBlogPost");
//        return "layout";
//    }
//
//    @PostMapping("/add")
//    public String saveBlogPost(@RequestParam String title, @RequestParam String content) {
//        BlogPost post = new BlogPost();
//        post.setTitle(title);
//        post.setContent(content);
//        blogPostService.addPost(post);
//        return "redirect:/blog";
//    }

