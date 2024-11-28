package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Product;

public interface ProductService {
    List<Product> getProducts();

    Product getProductById(String id);

    Product save(Product product);

    void delete(String id);
}
