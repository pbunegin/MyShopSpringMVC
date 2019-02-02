package org.shop.service;

import org.shop.data.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(Long categoryId);
    Category getCategoryByName(String categoryName);
    Long createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long categoryId);
}
