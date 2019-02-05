package org.shop.repository.impl;

import org.hibernate.SessionFactory;
import org.shop.data.Order;
import org.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    private SessionFactory sessionFactory;
//    @PersistenceContext
//    private EntityManager em;

    @Override
    public Long createOrder(Order order) {
        return (Long) sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public void updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    public List<Order> getOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }
}
