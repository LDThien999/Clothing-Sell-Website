package com.example.clothing_sell_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {}
