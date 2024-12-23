package com.example.clothing_sell_website.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.clothing_sell_website.entity.OrderList;
import com.example.clothing_sell_website.repository.OrderListRepository;
import com.example.clothing_sell_website.service.customer.OrderListService;

@org.springframework.stereotype.Service
public class OrderListServiceImpl implements OrderListService {
    @Autowired
    OrderListRepository OrderListRepo;

    @Override
    public void saveOrderList(OrderList orderList) {
        OrderListRepo.save(orderList);
    }

    @Override
    public List<OrderList> getOrderListByOrder(int orderId) {
        return OrderListRepo.getOrderListByOrder(orderId);
    }
}
