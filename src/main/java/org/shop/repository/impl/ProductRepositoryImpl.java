package org.shop.repository.impl;

import org.hibernate.SessionFactory;
import org.shop.data.Product;
import org.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public Product getProductById(Long productId) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from products where id=?");
            preparedStatement.setLong(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            resultSet.getObject(Product);
        } catch (SQLException e) {
            LOGGER.error(e.getSQLState());
        }
//        return sessionFactory.getCurrentSession().get(Product.class, productId);
    }

    @Override
    public List<Product> getProducts() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from Product").list();
    }

    @Override
    public Long createProduct(Product product) {
//        Long id = (Long) sessionFactory.getCurrentSession().save(product);
//        product.refreshImgUrl();
//        return id;
    }

    @Override
    public void updateProduct(Product product) {
//        sessionFactory.getCurrentSession().update(product);
//        product.refreshImgUrl();
    }

    @Override
    public void deleteProduct(Long productId) {
//        sessionFactory.getCurrentSession().delete(getProductById(productId));
    }
}
