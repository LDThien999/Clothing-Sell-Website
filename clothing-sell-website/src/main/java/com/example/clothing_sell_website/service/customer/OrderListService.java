package com.example.clothing_sell_website.service.customer;
import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.OrderList;

import java.util.List;


public interface OrderListService {
    void saveOrderList(OrderList orderList);
    List<OrderList> getOrderListByOrder(int OrderId);
}