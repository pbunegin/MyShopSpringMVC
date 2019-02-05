package org.shop.service.impl;

import org.shop.data.Basket;
import org.shop.repository.BasketRepository;
import org.shop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    BasketRepository basketRepository;

    @Override
    public Long createBasket(Basket basket) {
        return basketRepository.createBasket(basket);
    }

    @Override
    public Basket getBasketById(Long id) {
        return basketRepository.getBasketById(id);
    }

    @Override
    public void updateBasket(Basket basket) {
        basketRepository.updateBasket(basket);
    }

    @Override
    public List<Basket> getBaskets() {
        return basketRepository.getBaskets();
    }
}
