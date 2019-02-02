package org.shop.repository.impl;

import org.hibernate.Session;
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
        List<Product> products = sessionFactory.getCurrentSession()
                .createSQLQuery("select * from products").addEntity(Product.class).list();
        return products;
    }

    @Override
    public Long createProduct(Product product) {
        product.refreshImgUrl();
        return (Long) sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void updateProduct(Product product) {
        product.refreshImgUrl();
        sessionFactory.getCurrentSession().merge(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, productId);
        session.delete(product);
    }
}
