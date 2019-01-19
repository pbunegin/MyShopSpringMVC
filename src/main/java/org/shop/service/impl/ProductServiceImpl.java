package org.shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.shop.service.ProductService;
import org.shop.data.Product;
import org.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product getProductById(long id) {
        return repository.getProductById(id);
    }

    @Override
    public List<Product> getProducts() {
        return repository.getProducts();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return repository.getProducts().stream().filter(product -> product.getProductName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public long createProduct(Product product) {
        return repository.createProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    @Override
    public void deleteProduct(long productId) {
        repository.deleteProduct(productId);
    }

    @Override
    public void createOrUpdateProduct(Product product) {
        Product product1 = getProductById(product.getId());
        if (product1 == null){
            createProduct(product);
        } else {
            updateProduct(product);
        }
    }
}
