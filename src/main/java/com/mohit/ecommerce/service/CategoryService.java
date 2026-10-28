package com.mohit.ecommerce.service;

import com.mohit.ecommerce.entity.Category;
import com.mohit.ecommerce.exception.ResourceNotFoundException;
import com.mohit.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) { this.repository = repository; }

    public Category create(Category category) {
        if (repository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("A category with this name already exists");
        }
        return repository.save(category);
    }

    public List<Category> getAll() { return repository.findAll(); }

    public Category getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    }

    public Category update(Long id, Category input) {
        Category category = getById(id);
        category.setName(input.getName());
        return repository.save(category);
    }

    public void delete(Long id) {
        Category category = getById(id);
        repository.delete(category);
    }
}
