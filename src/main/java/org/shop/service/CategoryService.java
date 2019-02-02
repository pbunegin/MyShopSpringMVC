package org.shop.service;

import org.shop.data.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(long categoryId);
    Category getCategoryByName(String categoryName);
    long createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(long categoryId);
}
