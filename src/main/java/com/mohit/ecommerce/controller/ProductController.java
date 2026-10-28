package com.mohit.ecommerce.controller;

import com.mohit.ecommerce.entity.Product;
import com.mohit.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product,
                          @RequestParam Long categoryId) {
        return service.create(product, categoryId);
    }

    @GetMapping
    public List<Product> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product product,
                          @RequestParam Long categoryId) {
        return service.update(id, product, categoryId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) { return service.search(name); }

    @GetMapping("/by-category/{categoryId}")
    public List<Product> byCategory(@PathVariable Long categoryId) {
        return service.byCategory(categoryId);
    }
}
