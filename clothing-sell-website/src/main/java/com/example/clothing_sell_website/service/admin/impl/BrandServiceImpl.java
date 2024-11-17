package com.example.clothing_sell_website.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.repository.BrandRepository;
import com.example.clothing_sell_website.service.admin.BrandService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findBrandById(String id) {
        return brandRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thương hiệu với id là " + id));
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void delete(String id) {
        brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thuương hiệu này"));
        brandRepository.deleteById(id);
    }
}
