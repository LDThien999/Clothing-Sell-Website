package com.example.clothing_sell_website.service.customer.impl;


import com.example.clothing_sell_website.entity.LevelOfInterest;
import com.example.clothing_sell_website.repository.LevelOfInterestRepository;
import com.example.clothing_sell_website.service.customer.LevelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelOfInterestRepository lvRepository;
    @Override
    public LevelOfInterest getLVByCusPro(String customerId, String productId){
    return lvRepository.getLVByCusPro(customerId, productId);
    }
    @Override
    public LevelOfInterest saveLV(LevelOfInterest lv){
        return lvRepository.save(lv);
    }

}

