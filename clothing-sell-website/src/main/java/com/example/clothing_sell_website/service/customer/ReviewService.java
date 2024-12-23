package com.example.clothing_sell_website.service.customer;

import java.util.List;

import com.example.clothing_sell_website.entity.Review;

public interface ReviewService {
    List<Review> getReviewByPro(String productId);

    Review getReviewByProCus(String productId, String customerId);

    Review saveReview(Review review);
}
