package com.example.clothing_sell_website.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.clothing_sell_website.entity.Product;

public interface PageRepository extends PagingAndSortingRepository<Product, String> {}
