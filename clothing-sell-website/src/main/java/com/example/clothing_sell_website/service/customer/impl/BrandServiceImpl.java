package com.example.clothing_sell_website.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.repository.BrandRepository;
import com.example.clothing_sell_website.service.customer.BrandService;

@org.springframework.stereotype.Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepo;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepo.findAll();
    }
}
