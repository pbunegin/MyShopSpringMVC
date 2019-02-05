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
    OrderRepository orderRepository;

    @Override
    public Long createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }
}
