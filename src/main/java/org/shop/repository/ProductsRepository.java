package org.shop.repository;

import java.util.List;

import org.shop.data.Category;
import org.shop.data.Product;

public interface ProductsRepository {
    
    Product getProductById(int productId);
    
    List<Product> getProducts();
    
    List<Product> getProductsByName(String name);
    
    int createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(Long productId);

    void createCategory(Category category);

    List<Category> getCategories();
}
