package org.shop.repository.list;

import org.shop.data.Category;
import org.shop.data.Product;
import org.shop.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsListRepository implements ProductsRepository {
    private List<Category> categories = new ArrayList<>();

    @Override
    public Product getProductById(Long productId) {
        return getProducts().stream().filter(product -> product.getId()==productId).findFirst().get();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        categories.forEach(category -> result.addAll(category.getProducts()));
        return result;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return getProducts().stream().filter(product -> product.getProductName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Long createProduct(Product product) {
//        return categories.get(categories.size()-1).getProducts().add(product);
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
