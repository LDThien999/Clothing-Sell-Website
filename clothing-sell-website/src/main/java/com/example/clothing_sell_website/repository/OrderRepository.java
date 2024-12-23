package com.example.clothing_sell_website.repository;

import java.util.List;

import com.example.clothing_sell_website.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.OrderList;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("FROM OrderList WHERE order.orderId = :orderId")
    List<OrderList> getOrderListsByOrderId(@Param("orderId") Integer orderId);

    List<Order> findByStatus(Boolean status);

    List<Order> findOrdersByCustomer(Customer customer);
}
