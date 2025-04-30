package dev.jannat.BrewBreeze.service;

import dev.jannat.BrewBreeze.models.BlogPost;
import dev.jannat.BrewBreeze.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;


    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost addPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public void deletePostById(Long id) {

        blogPostRepository.deleteById(id);
    }

    public Optional<BlogPost> getPostById(Long id) {

        return blogPostRepository.findById(id);
    }
}
