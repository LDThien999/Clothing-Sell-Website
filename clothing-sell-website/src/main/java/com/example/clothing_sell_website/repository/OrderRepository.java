package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
