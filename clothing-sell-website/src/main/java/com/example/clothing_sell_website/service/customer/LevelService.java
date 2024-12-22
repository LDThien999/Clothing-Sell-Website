package com.example.clothing_sell_website.service.customer;
import com.example.clothing_sell_website.entity.LevelOfInterest;
import java.util.List;



public interface LevelService {
    LevelOfInterest getLVByCusPro(String customerId, String productId);
    LevelOfInterest saveLV(LevelOfInterest lv);
}