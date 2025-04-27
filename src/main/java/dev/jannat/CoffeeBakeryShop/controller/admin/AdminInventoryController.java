package dev.jannat.CoffeeBakeryShop.controller.admin;

import dev.jannat.CoffeeBakeryShop.models.Product;
import dev.jannat.CoffeeBakeryShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/inventory")
public class AdminInventoryController {

    private final ProductService productService;

    public AdminInventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String viewInventory(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("title", "Inventory");
        model.addAttribute("content", "admin/inventory");
        return "admin/layout";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/inventory";
    }
}
