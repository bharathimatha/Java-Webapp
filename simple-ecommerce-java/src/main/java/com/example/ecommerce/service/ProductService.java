package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = List.of(
        new Product(1L, "Wireless Mouse", "Comfortable wireless mouse", new BigDecimal("599.00")),
        new Product(2L, "Keyboard", "USB keyboard for everyday use", new BigDecimal("899.00")),
        new Product(3L, "Headphones", "Over-ear wired headphones", new BigDecimal("1299.00")),
        new Product(4L, "Laptop Stand", "Adjustable aluminium laptop stand", new BigDecimal("1499.00"))
    );

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
