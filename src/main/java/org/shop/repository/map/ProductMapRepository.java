package org.shop.repository.map;

import java.util.ArrayList;
import java.util.List;

import org.shop.data.Product;
import org.shop.repository.ProductRepository;

public class ProductMapRepository extends AbstractMapRepository<Product> implements ProductRepository {

    @Override
    public Product getProductById(Long productId) {
        return get(productId);
    }
    
    @Override
    public List<Product> getProducts() {
        return new ArrayList<Product>(register.values());
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
