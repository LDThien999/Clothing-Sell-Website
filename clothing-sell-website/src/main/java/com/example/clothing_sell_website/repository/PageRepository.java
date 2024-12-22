package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageRepository extends PagingAndSortingRepository<Product, String> {
}

