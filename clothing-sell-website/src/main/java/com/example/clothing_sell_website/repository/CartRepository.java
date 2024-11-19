package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.Cart;
import com.example.clothing_sell_website.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("FROM Cart c WHERE c.customer.customerId = :customerId and c.product.productId = :productId ")
    Cart getCartByCP(@Param("customerId") String customerId, @Param("productId") String productId);

    @Modifying
    @Query("FROM Cart c WHERE c.customer.customerId = :customerId")
    List <Cart> getCartByCus(@Param("customerId") String customerId);
}
