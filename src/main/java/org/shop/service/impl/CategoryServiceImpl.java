package org.shop.service.impl;

import org.shop.data.Category;
import org.shop.repository.CategoryRepository;
import org.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getCategories() {
        return repository.getCategories();
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return repository.getCategoryById(categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return repository.getCategoryByName(categoryName);
    }

    @Override
    public long createCategory(Category category) {
        return repository.createCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        repository.updateCategory(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        repository.deleteCategory(categoryId);
    }
}
