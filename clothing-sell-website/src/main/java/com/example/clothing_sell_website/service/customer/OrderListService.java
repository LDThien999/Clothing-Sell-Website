package com.example.clothing_sell_website.service.customer;

import java.util.List;

import com.example.clothing_sell_website.entity.OrderList;

public interface OrderListService {
    void saveOrderList(OrderList orderList);

    List<OrderList> getOrderListByOrder(int OrderId);
}
