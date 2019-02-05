package org.shop.repository;

import org.shop.data.Order;

import java.util.List;

public interface OrderRepository {
    Long createOrder(Order order);

    Order getOrderById(Long id);

    void updateOrder(Order order);

    List<Order> getOrders();
}
