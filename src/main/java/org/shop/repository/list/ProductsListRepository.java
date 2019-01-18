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
    private long sequence = 0;

    @Override
    public Product getProductById(long productId) {
        return getProducts().stream().filter(product -> product.getId()==productId).findFirst().orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        categories.forEach(category -> result.addAll(category.getProducts()));
        return result;
    }

    @Override
    public long createProduct(String categoryName, Product product) {
        product.setId(++sequence);
        Category category = getCategoryByName(categoryName);
        if (category != null){
            category.getProducts().add(product);
        } else {
            category = new Category(categoryName);
            category.getProducts().add(product);
            createCategory(category);
        }
        categories.get(categories.size()-1).getProducts().add(product);
        return product.getId();
    }

    @Override
    public void updateProduct(Product product) {
        Product productForUpdate = getProductById(product.getId());
        productForUpdate = product;
    }

    @Override
    public void deleteProduct(Long productId) {
        for (int i = 0; i < categories.size(); i++) {
            List<Product> products = categories.get(i).getProducts();
            for (int j = 0; j < products.size(); j++) {
                if (products.get(j).getId()==productId){
                    products.remove(j);
                    return;
                }
            }
            if (products.isEmpty()){
                categories.remove(i);
            }
        }
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
