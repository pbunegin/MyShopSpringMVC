package org.shop.repository.list;

import org.shop.data.Product;
import org.shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class ProductMapRepository implements ProductRepository {
    private Map<Long, Product> products = new HashMap<>();
    private long sequence = 0;

    @Override
    public Product getProductById(long productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(this.products.values());
    }

    @Override
    public long createProduct(Product product) {
        product.setId(++sequence);
        product.refreshImgUrl();
        products.put(product.getId(), product);
        return product.getId();
    }

    @Override
    public void updateProduct(Product product) {
        product.refreshImgUrl();
        products.put(product.getId(),product);
    }

    @Override
    public void deleteProduct(long productId) {
        products.remove(productId);
    }
}
