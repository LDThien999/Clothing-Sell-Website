package com.example.clothing_sell_website.service.admin;

import com.example.clothing_sell_website.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(String id);
    public Product save(Product product);
    public void delete(String id);
}