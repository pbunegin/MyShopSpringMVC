package org.shop.service;

import java.util.List;

import org.shop.data.Product;

public interface ProductService {
    
    Product getProductById(long id);

    List<Product> getProducts();
    
    List<Product> getProductsByName(String name);
    
    long createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(long productId);

    void createOrUpdateProduct(Product product);
}
