package dev.jannat.CoffeeBakeryShop.service;

import dev.jannat.CoffeeBakeryShop.models.Product;
import dev.jannat.CoffeeBakeryShop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        // Check if the product exists
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    // This method fetches products by category
    public List<Product> getProductsByCategory(Product.ProductCategory category) {
        return productRepository.findByCategory(category);
    }
}
