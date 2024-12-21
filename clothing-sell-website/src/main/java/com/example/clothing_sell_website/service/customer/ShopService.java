package com.example.clothing_sell_website.service.customer;
import com.example.clothing_sell_website.entity.Product;
import java.util.List;



public interface ShopService {
    List<Product> getAllProduct();
    List<Product> getProductByBrand(String brandId);
    List<Product> getProductByType(String typeId);
    Product getProductById(String productId);
}
