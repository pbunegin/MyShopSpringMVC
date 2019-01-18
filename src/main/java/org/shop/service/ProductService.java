package org.shop.service;

import java.util.List;

import org.shop.data.Category;
import org.shop.data.Product;

public interface ProductService {
    
    Product getProductById(long id);

    List<Product> getProducts();
    
    List<Product> getProductsByName(String name);
    
    long createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(Long productId);

    void createCategory(Category category);

    List<Category> getCategories();

    Category getCategoryByName(String categoryName);

    Product createOrUpdateProduct(String categoryName, Product product);
}
