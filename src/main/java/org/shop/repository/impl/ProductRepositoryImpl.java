package org.shop.repository.impl;

import org.hibernate.SessionFactory;
import org.shop.data.Product;
import org.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product getProductById(Long productId) {
        return sessionFactory.getCurrentSession().get(Product.class, productId);
    }

    @Override
    public List<Product> getProducts() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product").list();
    }

    @Override
    public Long createProduct(Product product) {
        Long id = (Long) sessionFactory.getCurrentSession().save(product);
        product.refreshImgUrl();
        return id;
    }

    @Override
    public void updateProduct(Product product) {
        sessionFactory.getCurrentSession().update(product);
        product.refreshImgUrl();
    }

    @Override
    public void deleteProduct(Long productId) {
        sessionFactory.getCurrentSession().delete(getProductById(productId));
    }
}
