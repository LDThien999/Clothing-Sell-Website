package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.OrderList;
import com.example.clothing_sell_website.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Role;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("FROM Review r WHERE r.product.productId = :productId ")
    List<Review> getReviewByPro(@Param("productId") String productId);
}
