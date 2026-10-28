package com.mohit.ecommerce.service;

import com.mohit.ecommerce.entity.Category;
import com.mohit.ecommerce.entity.Product;
import com.mohit.ecommerce.exception.ResourceNotFoundException;
import com.mohit.ecommerce.repository.CategoryRepository;
import com.mohit.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository products;
    private final CategoryRepository categories;

    public ProductService(ProductRepository products, CategoryRepository categories) {
        this.products = products;
        this.categories = categories;
    }

    public Product create(Product product, Long categoryId) {
        Category category = categories.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
        product.setId(null);
        product.setCategory(category);
        return products.save(product);
    }

    public List<Product> getAll() { return products.findAll(); }

    public Product getById(Long id) {
        return products.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    public Product update(Long id, Product input, Long categoryId) {
        Product existing = getById(id);
        Category category = categories.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
        existing.setName(input.getName());
        existing.setDescription(input.getDescription());
        existing.setPrice(input.getPrice());
        existing.setStock(input.getStock());
        existing.setCategory(category);
        return products.save(existing);
    }

    public void delete(Long id) { products.delete(getById(id)); }

    public List<Product> search(String name) { return products.findByNameContainingIgnoreCase(name); }

    public List<Product> byCategory(Long categoryId) {
        if (!categories.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found: " + categoryId);
        }
        return products.findByCategoryId(categoryId);
    }
}
