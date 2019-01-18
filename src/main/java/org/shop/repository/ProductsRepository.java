package org.shop.repository;

import java.util.List;

import org.shop.data.Category;
import org.shop.data.Product;

public interface ProductsRepository {
    
    Product getProductById(long productId);
    
    List<Product> getProducts();
    
    long createProduct(String categoryName, Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(Long productId);

    void createCategory(Category category);

    List<Category> getCategories();
}
