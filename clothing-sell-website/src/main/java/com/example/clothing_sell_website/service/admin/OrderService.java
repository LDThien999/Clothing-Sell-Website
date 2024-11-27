package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Order;

public interface OrderService {
    public List<Order> getOrders();
        public Order findOrderById(Integer id);
        public Order save(Order order);
//        public Order update(Integer id);
    //    public void delete(String id);
}
