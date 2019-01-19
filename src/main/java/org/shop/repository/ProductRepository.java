package org.shop.repository;

import java.util.List;

import org.shop.data.Product;

public interface ProductRepository {
    
    Product getProductById(long productId);
    
    List<Product> getProducts();
    
    long createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(long productId);
}
