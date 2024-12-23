package com.example.clothing_sell_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.OrderList;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {
    @Modifying
    @Query("FROM OrderList ol WHERE ol.order.orderId = :orderId")
    List<OrderList> getOrderListByOrder(@Param("orderId") int orderId);
}
