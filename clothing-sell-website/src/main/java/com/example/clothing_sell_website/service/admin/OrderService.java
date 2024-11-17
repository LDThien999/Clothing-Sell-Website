package com.example.clothing_sell_website.service.admin;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.Type;

import java.util.List;

public interface OrderService {
    public List<Order> getOrders();
//    public Type findTypeById(String id);
//    public Type save(Type type);
//    public void delete(String id);
}