package com.example.clothing_sell_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {}
