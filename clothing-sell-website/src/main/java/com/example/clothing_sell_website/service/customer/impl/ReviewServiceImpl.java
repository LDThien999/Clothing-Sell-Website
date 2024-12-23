package com.example.clothing_sell_website.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.clothing_sell_website.entity.Review;
import com.example.clothing_sell_website.repository.ReviewRepository;
import com.example.clothing_sell_website.service.customer.ReviewService;

@org.springframework.stereotype.Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;

    @Override
    public List<Review> getReviewByPro(String productId) {
        return reviewRepo.getReviewByPro(productId);
    }

    @Override
    public Review getReviewByProCus(String productId, String customerId) {
        return reviewRepo.getReviewByProCus(productId, customerId);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }
}
