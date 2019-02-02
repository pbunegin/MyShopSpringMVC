package org.shop.repository;

import org.shop.data.Category;

import java.util.List;

public interface CategoryRepository {

    Category getCategoryById(Long categoryId);

    Category getCategoryByName(String categoryName);

    List<Category> getCategories();

    Long createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long categoryId);
}
