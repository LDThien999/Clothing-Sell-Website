package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Brand;

public interface BrandService {
    List<Brand> getBrands();

    Brand findBrandById(String id);

    Brand save(Brand brand);

    void delete(String id);
}
