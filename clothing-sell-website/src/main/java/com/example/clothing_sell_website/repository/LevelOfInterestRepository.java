package com.example.clothing_sell_website.repository;

import java.util.List;
import java.util.Optional;

import com.example.clothing_sell_website.entity.LevelOfInterest;
import com.example.clothing_sell_website.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Customer;

@Repository
public interface LevelOfInterestRepository extends JpaRepository<LevelOfInterest, Integer> {

    @Query("FROM LevelOfInterest lv WHERE lv.customer.customerId = :customerId AND lv.product.productId = :productId")
    LevelOfInterest getLVByCusPro(@Param("customerId") String customerId, @Param("productId") String productId);
}

