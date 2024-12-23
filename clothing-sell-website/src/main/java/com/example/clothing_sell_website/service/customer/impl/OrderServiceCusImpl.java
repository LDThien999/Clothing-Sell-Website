package com.example.clothing_sell_website.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.repository.OrderRepository;
import com.example.clothing_sell_website.service.customer.OrderCusService;

@org.springframework.stereotype.Service
public class OrderServiceCusImpl implements OrderCusService {
    @Autowired
    OrderRepository OrderRepo;

    @Override
    public void saveOrder(Order order) {
        OrderRepo.save(order);
    }
}
