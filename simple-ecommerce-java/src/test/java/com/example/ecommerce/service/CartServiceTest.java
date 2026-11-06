package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {
    private final ProductService productService = new ProductService();

    @Test
    void shouldAddProductAndCalculateTotal() {
        CartService cart = new CartService();
        Product product = productService.findById(1L).orElseThrow();

        cart.add(product);
        cart.add(product);

        assertEquals(2, cart.getQuantity());
        assertEquals(new BigDecimal("1198.00"), cart.getTotal(productService));
    }

    @Test
    void shouldRemoveProduct() {
        CartService cart = new CartService();
        Product product = productService.findById(1L).orElseThrow();

        cart.add(product);
        cart.remove(1L);

        assertEquals(0, cart.getQuantity());
    }
}
