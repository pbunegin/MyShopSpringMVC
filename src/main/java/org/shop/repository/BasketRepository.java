package org.shop.repository;

import org.shop.data.Basket;

import java.util.List;

public interface BasketRepository {
    Long createBasket(Basket basket);

    Basket getBasketById(Long id);

    void updateBasket(Basket basket);

    List<Basket> getBaskets();
}
