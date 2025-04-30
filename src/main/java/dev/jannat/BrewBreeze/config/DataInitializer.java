package dev.jannat.BrewBreeze.config;

import dev.jannat.BrewBreeze.models.Product;
import dev.jannat.BrewBreeze.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public enum ProductCategory {
        BEVERAGE, COFFEE, DESSERT, DRINK, SPECIALTY, MILKSHAKE, BOBATEA, PEARLCOFFEE

    }

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Espresso", "Strong and rich coffee.", 2.50,
                    "https://example.com/espresso.jpg", Product.ProductCategory.BEVERAGE));
            productRepository.save(new Product("Latte", "Smooth blend of espresso and milk.", 3.50,
                    "https://example.com/latte.jpg", Product.ProductCategory.BEVERAGE));
            productRepository.save(new Product("Croissant", "Buttery flaky pastry.", 2.00,
                    "https://example.com/croissant.jpg", Product.ProductCategory.DESSERT));
            productRepository.save(new Product("Espresso", "Strong and rich coffee.", 2.50,
                    "https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5", Product.ProductCategory.BEVERAGE));
            productRepository.save(new Product("Latte", "Smooth blend of espresso and milk.", 3.50,
                    "https://images.unsplash.com/photo-1568649929103-28ffbefaca1e", Product.ProductCategory.BEVERAGE));

            // DESSERTS
            productRepository.save(new Product("Chocolate Cake", "Rich chocolate layers", 4.50,
                    "https://example.com/chocolatecake.jpg", Product.ProductCategory.DESSERT));
            productRepository.save(new Product("Cheesecake", "Creamy New York style", 5.00,
                    "https://example.com/cheesecake.jpg", Product.ProductCategory.DESSERT));

            // DRINKS
            productRepository.save(new Product("Iced Tea", "Refreshing herbal blend", 2.75,
                    "https://example.com/icedtea.jpg", Product.ProductCategory.DRINK));
            productRepository.save(new Product("Lemonade", "Homemade citrus drink", 3.25,
                    "https://example.com/lemonade.jpg", Product.ProductCategory.DRINK));

            // SPECIALTY
            productRepository.save(new Product("Affogato", "Espresso over vanilla gelato", 5.50,
                    "https://example.com/affogato.jpg", Product.ProductCategory.SPECIALTY));
            productRepository.save(new Product("Irish Coffee", "Coffee with whiskey cream", 6.50,
                    "https://example.com/irishcoffee.jpg", Product.ProductCategory.SPECIALTY));

            // NEW CATEGORIES
            // MILKSHAKES
            productRepository.save(new Product("Vanilla Shake", "Creamy vanilla milkshake", 4.75,
                    "https://example.com/vanillashake.jpg", Product.ProductCategory.MILKSHAKE));
            productRepository.save(new Product("Chocolate Shake", "Rich chocolate delight", 5.25,
                    "https://example.com/chocolateshake.jpg", Product.ProductCategory.MILKSHAKE));

            // BOBATEA (Bubble Tea)
            productRepository.save(new Product("Classic Milk Tea", "With tapioca pearls", 4.50,
                    "https://example.com/milktea.jpg", Product.ProductCategory.BOBATEA));
            productRepository.save(new Product("Taro Bubble Tea", "Purple taro flavor", 5.00,
                    "https://example.com/tarotea.jpg", Product.ProductCategory.BOBATEA));

            // PEARLCOFFEE (Coffee with pearls)
            productRepository.save(new Product("Coffee Pearl Latte", "Latte with coffee jelly", 5.50,
                    "https://example.com/pearllatte.jpg", Product.ProductCategory.PEARLCOFFEE));
            productRepository.save(new Product("Caramel Pearl Coffee", "Caramel coffee with pearls", 5.75,
                    "https://example.com/caramelpearl.jpg", Product.ProductCategory.PEARLCOFFEE));
            // Add imageUrl for all products
        }
    }

}
