package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.OrderList;

public interface OrderService {
    List<Order> getOrders();

    List<Order> getOrdersByCustomer(Customer customer);

    Order findOrderById(Integer id);

    Order save(Order order);

    List<OrderList> getOrderListsByOrderId(Integer id);

    List<Order> getOrdersByStatus(Boolean status);
    //        public Order update(Integer id);
    //    public void delete(String id);
}
