package com.example.clothing_sell_website.service.customer.impl;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.OrderList;
import com.example.clothing_sell_website.repository.OrderListRepository;
import com.example.clothing_sell_website.repository.OrderRepository;
import com.example.clothing_sell_website.service.customer.OrderCusService;
import com.example.clothing_sell_website.service.customer.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class OrderListServiceImpl implements OrderListService {
    @Autowired
    OrderListRepository OrderListRepo;

    @Override
    public void saveOrderList(OrderList orderList) {
        OrderListRepo.save(orderList);
    }

    @Override
    public List<OrderList> getOrderListByOrder(int orderId){
        return OrderListRepo.getOrderListByOrder(orderId);
    }

}