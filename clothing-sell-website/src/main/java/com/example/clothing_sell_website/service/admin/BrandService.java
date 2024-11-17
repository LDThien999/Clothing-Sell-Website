package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Brand;

public interface BrandService {
    public List<Brand> getBrands();

    public Brand findBrandById(String id);

    public Brand save(Brand brand);

    public void delete(String id);
}
