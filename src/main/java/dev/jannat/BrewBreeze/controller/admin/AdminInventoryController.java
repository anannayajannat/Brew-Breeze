package dev.jannat.BrewBreeze.controller.admin;

import dev.jannat.BrewBreeze.models.Product;
import dev.jannat.BrewBreeze.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/inventory")
public class AdminInventoryController {

    private final ProductService productService;

    public AdminInventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> viewInventory() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
