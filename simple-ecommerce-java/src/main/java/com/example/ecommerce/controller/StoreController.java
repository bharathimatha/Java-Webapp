package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoreController {
    private final ProductService productService;
    private final CartService cartService;

    public StoreController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartQuantity", cartService.getQuantity());
        return "index";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        cartService.add(product);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("productService", productService);
        model.addAttribute("total", cartService.getTotal(productService));
        return "cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Model model) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("total", cartService.getTotal(productService));
        cartService.clear();
        return "order-success";
    }
}
