package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    private final Map<Long, Integer> items = new LinkedHashMap<>();

    public void add(Product product) {
        items.merge(product.getId(), 1, Integer::sum);
    }

    public void remove(Long productId) {
        items.remove(productId);
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public int getQuantity() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public BigDecimal getTotal(ProductService productService) {
        return items.entrySet().stream()
            .map(entry -> productService.findById(entry.getKey())
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .orElse(BigDecimal.ZERO))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }
}
