package com.example.clothing_sell_website.service.customer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.clothing_sell_website.entity.Product;

public interface ShopService {
    List<Product> getAllProduct();

    List<Product> getProductByBrand(String brandId);

    List<Product> getProductByType(String typeId);

    Product getProductById(String productId);

    List<String> getTop20ProductIds(int n);

    List<Product> getTop20Products(int n);

    Page<Product> getProductsByPage(int page, int size);
}
