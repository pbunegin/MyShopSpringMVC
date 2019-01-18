package org.shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.shop.data.Category;
import org.shop.service.ProductService;
import org.shop.data.Product;
import org.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository repository;

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
    public void deleteProduct(Long productId) {
        repository.deleteProduct(productId);
    }

    @Override
    public void createCategory(Category category) {
        repository.createCategory(category);
    }

    @Override
    public List<Category> getCategories() {
        return repository.getCategories();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return getCategories().stream().filter(category -> category.getCategoryName().equals(categoryName)).findFirst().orElse(null);
    }

    @Override
    public Product createOrUpdateProduct(String categoryName, Product product) {
        product.setImgUrl();
        Product productFromRepository = getProductById(product.getId());
        if (productFromRepository != null){
            productFromRepository = product;
        } else {
        }
        return product;
    }
}
