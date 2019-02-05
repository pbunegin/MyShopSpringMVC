package org.shop.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.shop.service.ProductService;
import org.shop.data.Product;
import org.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

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
        deleteFile(productId);
    }

    @Override
    public void createOrUpdateProduct(Product product, MultipartFile file) {
        if (product.getId() == null) {
            createProduct(product);
        } else {
            updateProduct(product);
        }
        saveFile(product.getId(), file);
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

    @Override
    public List<Product> getProducts(List<Long> idList) {
        List<Product> products = new ArrayList<>();
        for (Long id: idList){
            products.add(getProductById(id));
        }
        return products;
    }

    private void saveFile(Long id, MultipartFile file) {
        try {
            Files.write(Paths.get("target/classes/static/prodImg/" + id + ".jpg"),
                    file.getBytes(), StandardOpenOption.CREATE);
            Files.write(Paths.get("src/main/resources/static/prodImg/" + id + ".jpg"),
                    file.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void deleteFile(Long id) {
        try {
            Files.delete(Paths.get("target/classes/static/prodImg/" + id + ".jpg"));
            Files.delete(Paths.get("src/main/resources/static/prodImg/" + id + ".jpg"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
