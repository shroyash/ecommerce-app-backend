package com.shroyash.multi_shop.service.implement;


import com.shroyash.multi_shop.model.Order;
import com.shroyash.multi_shop.repository.OrderRepository;
import com.shroyash.multi_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return List.of();
    }

}
