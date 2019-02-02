package org.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public Product getProductById(Long id) {
        return repository.getProductById(id);
    }

    @Override
    public List<Product> getProducts() {
        return repository.getProducts();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return repository.getProducts().stream()
                .filter(product -> product.getProductName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Long createProduct(Product product) {
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
    public void createOrUpdateProduct(Product product) {
        Product product1 = getProductById(product.getId());
        if (product1 == null) {
            createProduct(product);
        } else {
            updateProduct(product);
//            product1.setCharacteristic(product.getCharacteristic());
//            product1.setId(product.getId());
//            product1.setCategory(product.getCategory());
//            product1.setPrice(product.getPrice());
//            product1.setProductName(product.getProductName());
//            product1.refreshImgUrl();
//            updateProduct(product1);

        }
    }

    @Override
    public List<Long> getIdProductsByParam(Map<String, String> searchParam) {
        List<Long> result = new ArrayList<>();
        switch (searchParam.get("searchParam")) {
            case "byName": {
                result = getProducts().stream()
                        .filter(product -> product.getProductName().toLowerCase().contains(searchParam.get("searchString").toLowerCase()))
                        .map(Product::getId).distinct()
                        .collect(Collectors.toList());
                break;
            }
            case "byPrice": {
                result = getProducts().stream()
                        .filter(product -> product.getPrice() == Long.parseLong(searchParam.get("searchString")))
                        .map(Product::getId).distinct().collect(Collectors.toList());
                break;
            }
            case "byCharacteristic": {
                result = getProducts().stream()
                        .filter(product -> product.getCharacteristic().toLowerCase().contains(searchParam.get("searchString").toLowerCase()))
                        .map(Product::getId).distinct().collect(Collectors.toList());
                break;
            }
        }
        return result;
    }

    @Override
    public List<Long> createProducts(List<Product> products) {
        products.forEach(this::createProduct);
        return products.stream().map(Product::getId).distinct().collect(Collectors.toList());
    }
}
