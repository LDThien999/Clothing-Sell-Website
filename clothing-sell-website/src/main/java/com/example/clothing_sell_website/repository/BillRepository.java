package com.example.clothing_sell_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("SELECT SUM(ol.quantity * p.price)" + "FROM OrderList ol "
            + "JOIN Cart c ON ol.cart.cartId = c.cartId "
            + "JOIN Product p ON p.productId = c.product.productId "
            + "WHERE ol.order.orderId = :orderId")
    Float getTotalAmount(@Param("orderId") Integer orderId);
}
