package com.shroyash.multi_shop.service;



import com.shroyash.multi_shop.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrderList();

    Order getOrderById(Long orderId);

    Order createOrder(Order order);

    Order updateOrder(Long orderId, Order order);

    void deleteOrder(Long orderId);

    List<Order> getOrdersByUserId(Long userId);
}
