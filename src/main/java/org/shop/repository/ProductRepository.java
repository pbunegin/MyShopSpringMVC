package org.shop.repository;

import java.util.List;

import org.shop.data.Product;

public interface ProductRepository {
    
    Product getProductById(Long productId);
    
    List<Product> getProducts();
    
    Long createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(Long productId);
}
