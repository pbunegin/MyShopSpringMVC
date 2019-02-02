package org.shop.repository;

import org.shop.data.Category;

import java.util.List;

public interface CategoryRepository {

    Category getCategoryById(long categoryId);

    Category getCategoryByName(String categoryName);

    List<Category> getCategories();

    long createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(long categoryId);
}
