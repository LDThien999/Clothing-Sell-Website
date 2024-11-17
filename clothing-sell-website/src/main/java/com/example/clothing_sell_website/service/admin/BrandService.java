package com.example.clothing_sell_website.service.admin;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.entity.Product;

import java.util.List;

public interface BrandService {
    public List<Brand> getBrands();
    public Brand findBrandById(String id);
    public Brand save(Brand brand);
    public void delete(String id);
}