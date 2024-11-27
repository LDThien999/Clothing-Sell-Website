package com.example.clothing_sell_website.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.repository.OrderRepository;
import com.example.clothing_sell_website.service.admin.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Không tìm thấy đơn hàng với id là " + id));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
