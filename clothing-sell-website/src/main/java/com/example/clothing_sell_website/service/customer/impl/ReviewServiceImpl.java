package com.example.clothing_sell_website.service.customer.impl;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.entity.Review;
import com.example.clothing_sell_website.repository.OrderRepository;

import com.example.clothing_sell_website.repository.ReviewRepository;
import com.example.clothing_sell_website.service.customer.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;

    @Override
    public List<Review> getReviewByPro(String productId){
        return reviewRepo.getReviewByPro(productId);
    }
}