package com.example.clothing_sell_website.service.admin.impl;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.Type;
import com.example.clothing_sell_website.repository.OrderRepository;
import com.example.clothing_sell_website.repository.TypeRepository;
import com.example.clothing_sell_website.service.admin.OrderService;
import com.example.clothing_sell_website.service.admin.TypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
