package org.shop.service;

import java.util.List;
import java.util.Map;

import org.shop.data.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    
    Product getProductById(Long id);

    List<Product> getProducts();
    
    List<Product> getProductsByName(String name);
    
    Long createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProduct(Long productId);

    void createOrUpdateProduct(Product product, MultipartFile file);

    List<Long> getIdProductsByParam(Map<String, String> request);

    List<Long> createProducts(List<Product> products);
}
