package com.example.clothing_sell_website.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.customer.ShopService;

@RestController
public class PageController {

    @Autowired
    private ShopService shopService;

    // Endpoint phân trang trả về dữ liệu dưới dạng JSON
    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam int page, @RequestParam(defaultValue = "15") int size) {
        return shopService.getProductsByPage(page, size);
    }
}
