package org.shop.service;

import org.shop.data.Order;

import java.util.List;

public interface OrderService {
    Long createOrder(Order order);

    Order getOrderById(Long id);

    void updateOrder(Order order);

    List<Order> getOrders();
}
