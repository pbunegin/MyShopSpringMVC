package org.shop.repository.impl;

import org.hibernate.SessionFactory;
import org.shop.data.Basket;
import org.shop.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BasketRepositoryImpl implements BasketRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long createBasket(Basket basket) {
        return null;
    }

    @Override
    public Basket getBasketById(Long id) {
        return sessionFactory.getCurrentSession().get(Basket.class, id);
    }

    @Override
    public void updateBasket(Basket basket) {
        sessionFactory.getCurrentSession().update(basket);
    }

    @Override
    public List<Basket> getBaskets() {
        return sessionFactory.getCurrentSession().createQuery("from Basket").list();
    }
}
