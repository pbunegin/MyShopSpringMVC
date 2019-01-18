package org.shop.repository.list;

import org.shop.data.Category;
import org.shop.data.Product;
import org.shop.repository.ProductsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsListRepository implements ProductsRepository {
    private List<Category> categories = new ArrayList<>();

    @Override
    public Product getProductById(int productId) {
        return getProducts().stream().filter(product -> product.getId()==productId).findFirst().orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        categories.forEach(category -> result.addAll(category.getProducts()));
        return result;
    }

    @Override
    public int createProduct(Product product) {
        categories.get(categories.size()-1).getProducts().add(product);
        return product.getId();
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }
}
