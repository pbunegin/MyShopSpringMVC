package org.shop.service.impl;

import org.shop.data.Order;
import org.shop.repository.OrderRepository;
import org.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;

    @Override
    public Long createOrder(Order order) {
        return repository.createOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return repository.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        repository.updateOrder(order);
    }

    @Override
    public List<Order> getOrders() {
        return repository.getOrders();
    }
}
