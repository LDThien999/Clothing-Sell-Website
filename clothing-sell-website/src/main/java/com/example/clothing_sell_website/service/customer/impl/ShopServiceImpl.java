package com.example.clothing_sell_website.service.customer.impl;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.customer.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.clothing_sell_website.repository.ShopRepository;
import com.example.clothing_sell_website.service.customer.impl.ShopServiceImpl;
import java.util.List;

@org.springframework.stereotype.Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepo;
    @Override
    public List<Product> getAllProduct(){
        return shopRepo.findAll();
    }
}
