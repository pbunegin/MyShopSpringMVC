package org.shop.repository.map;

import java.util.ArrayList;
import java.util.List;

import org.shop.data.Product;
import org.shop.repository.ProductsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductsMapRepository extends AbstractMapRepository<Product> implements ProductsRepository {

    @Override
    public Product getProductById(Long productId) {
        return get(productId);
    }
    
    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(register.values());
    }
    
    @Override
    public List<Product> getProductsByName(String name) {
        return select(name);
    }

    @Override
    public Long createProduct(Product product) {
        return create(product);
    }

    @Override
    public void updateProduct(Product product) {
        update(product);
    }
    
    @Override
    public void deleteProduct(Long productId) {
        delete(productId);
    }

}
