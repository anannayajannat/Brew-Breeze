package dev.jannat.CoffeeBakeryShop.controller;

import dev.jannat.CoffeeBakeryShop.models.Product;
import dev.jannat.CoffeeBakeryShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("title", "Products");
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("content", "products/list");
        return "layout";
    }

    @GetMapping("/{category}")
    public String listByCategory(@PathVariable String category, Model model) {
        Product.ProductCategory productCategory = Product.ProductCategory.valueOf(category.toUpperCase());
        model.addAttribute("title", category);
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        model.addAttribute("content", "products/list");
        return "layout";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElse(null); // If product is not found, return null
        if (product == null) {
            return "redirect:/products";
        }


//        model.addAttribute("product", product);
//        return "products/detail";

        model.addAttribute("title", product.getName());
        model.addAttribute("product", product);
        model.addAttribute("content", "products/detail");
        return "layout";
    }
}