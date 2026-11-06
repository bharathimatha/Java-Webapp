package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private final ProductService productService = new ProductService();

    @Test
    void shouldReturnAllProducts() {
        assertEquals(4, productService.findAll().size());
    }

    @Test
    void shouldFindProductById() {
        Product product = productService.findById(1L).orElseThrow();
        assertEquals("Wireless Mouse", product.getName());
    }

    @Test
    void shouldReturnEmptyWhenProductDoesNotExist() {
        assertTrue(productService.findById(999L).isEmpty());
    }
}
