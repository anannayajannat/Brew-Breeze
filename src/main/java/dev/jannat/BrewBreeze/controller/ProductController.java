package dev.jannat.BrewBreeze.controller;

import dev.jannat.BrewBreeze.models.Product;
import dev.jannat.BrewBreeze.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private static final String IMAGE_UPLOAD_DIR = "product-images/";

    // Add a new product with image
    @PostMapping("/add")
    public Product addProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") double price,
                              @RequestParam("category") Product.ProductCategory category,
                              @RequestParam("image") MultipartFile image) throws IOException {

        // Save the image
        String imageFileName = image.getOriginalFilename();
        Path imagePath = Paths.get(IMAGE_UPLOAD_DIR, imageFileName);

        // Create the directory if it doesn't exist
        Files.createDirectories(imagePath.getParent());

        // Transfer the file to the server
        image.transferTo(imagePath.toFile());

        // Create and save the product object
        Product product = new Product(name, description, price, imagePath.toString(), category);

        // Save to the database
        return productRepository.save(product);
    }
}
