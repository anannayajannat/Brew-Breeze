package dev.jannat.CoffeeBakeryShop.config;

import dev.jannat.CoffeeBakeryShop.models.Product;
import dev.jannat.CoffeeBakeryShop.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                // Coffee
                productRepository.save(new Product("Espresso", "Strong black coffee", 2.50, "https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5", Product.ProductCategory.COFFEE));
                productRepository.save(new Product("Cappuccino", "Espresso with steamed milk foam", 3.50, "https://images.unsplash.com/photo-1534778101976-62847782c213", Product.ProductCategory.COFFEE));
                productRepository.save(new Product("Latte", "Espresso with a lot of steamed milk", 3.75, "https://images.unsplash.com/photo-1568649929103-28ffbefaca1e", Product.ProductCategory.COFFEE));
                productRepository.save(new Product("Mocha", "Espresso with chocolate and steamed milk", 4.00, "https://images.unsplash.com/photo-1603722051748-2f65fc084c43", Product.ProductCategory.COFFEE));

                // Bakery
                productRepository.save(new Product("Croissant", "Buttery, flaky pastry", 2.25, "https://images.unsplash.com/photo-1620146344904-097a0002d797", Product.ProductCategory.BAKERY));
                productRepository.save(new Product("Blueberry Muffin", "Sweet muffin with blueberries", 2.75, "https://images.unsplash.com/photo-1550583724-b2692b85b150", Product.ProductCategory.BAKERY));
                productRepository.save(new Product("Cinnamon Roll", "Sweet roll with cinnamon sugar", 3.25, "https://images.unsplash.com/photo-1608190003443-86ab6a8a7e8a", Product.ProductCategory.BAKERY));

                // Desserts
                productRepository.save(new Product("Chocolate Cake", "Rich chocolate layered cake", 4.50, "https://images.unsplash.com/photo-1606788075761-2c3454768b2b", Product.ProductCategory.BAKERY));
                productRepository.save(new Product("Cheesecake", "Creamy New York style cheesecake", 4.75, "https://images.unsplash.com/photo-1587300003388-59208cc962cb", Product.ProductCategory.BAKERY));

                // Shakes
                productRepository.save(new Product("Strawberry Milkshake", "Fresh strawberry milkshake", 3.50, "https://images.unsplash.com/photo-1621496206761-1538b29e8e7f", Product.ProductCategory.BAKERY));
                productRepository.save(new Product("Chocolate Milkshake", "Chocolate flavored milkshake", 3.75, "https://images.unsplash.com/photo-1619630510170-09fd7ff91377", Product.ProductCategory.BAKERY));

                // Lemonades
                productRepository.save(new Product("Classic Lemonade", "Refreshing classic lemonade", 2.00, "https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445", Product.ProductCategory.BAKERY));
                productRepository.save(new Product("Mint Lemonade", "Fresh lemonade with mint", 2.25, "https://images.unsplash.com/photo-1582552938357-0cfbdf065756", Product.ProductCategory.BAKERY));
            }
        };
    }
}
