package org.shop.service;

import org.shop.data.Basket;

import java.util.List;

public interface BasketService {
    Long createBasket(Basket basket);

    Basket getBasketById(Long id);

    void updateBasket(Basket basket);

    List<Basket> getBaskets();
}
