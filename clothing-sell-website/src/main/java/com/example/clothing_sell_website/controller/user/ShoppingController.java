package com.example.clothing_sell_website.controller.user;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.entity.Type;
import com.example.clothing_sell_website.service.customer.BrandService;
import com.example.clothing_sell_website.service.customer.ShopService;
import com.example.clothing_sell_website.service.customer.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandService brandService;
    @GetMapping("/shop.html")
    public String shop(Model model) {
        List<Product> products = shopService.getAllProduct();
        List<Type> types = typeService.getAllType();
        List<Brand> brands = brandService.getAllBrand();
        model.addAttribute("products", products);
        model.addAttribute("types", types);
        model.addAttribute("brands", brands);
        return "user/shopping/shop";
    }
}
