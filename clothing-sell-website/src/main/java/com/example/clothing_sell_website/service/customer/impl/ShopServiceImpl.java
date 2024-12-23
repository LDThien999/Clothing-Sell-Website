package com.example.clothing_sell_website.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.repository.PageRepository;
import com.example.clothing_sell_website.repository.ShopRepository;
import com.example.clothing_sell_website.service.customer.ShopService;

@org.springframework.stereotype.Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PageRepository pageRepo;

    @Override
    public List<Product> getAllProduct() {
        return shopRepo.findAll();
    }

    @Override
    public List<Product> getProductByBrand(String brandId) {
        return shopRepo.getProductByBrand(brandId);
    }

    @Override
    public List<Product> getProductByType(String typeId) {
        return shopRepo.getProductByType(typeId);
    }

    @Override
    public Product getProductById(String productId) {
        return shopRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại sản phẩm với id là " + productId));
    }

    // Truy vấn 10 Product_Id từ Level_Of_Interest
    @Override
    public List<String> getTop20ProductIds(int n) {
        String sql = "SELECT TOP 20 Product_Id " + "FROM Level_Of_Interest "
                + "WHERE Label_Main = "
                + n + " GROUP BY Product_Id "
                + "ORDER BY COUNT(*) DESC";

        // Thực hiện truy vấn SQL và lấy kết quả
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public List<Product> getTop20Products(int n) {
        List<String> productIds = getTop20ProductIds(n);
        return shopRepo.findAllById(productIds);
    }

    @Override
    public Page<Product> getProductsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pageRepo.findAll(pageable);
    }

    @Override
    public Product saveProduct(Product product){
        return shopRepo.save(product);
    }

    @Override
    public List<Product> getProductsHot(){
        return shopRepo.getProductHot();
    }

    @Override
    public List<Product> getHotProductsByVietNam(){
        return shopRepo.getProductHotByVietNam();
    }

    @Override
    public List<Product> getHotProductsByJP(){
        return shopRepo.getProductHotByJP();
    }


}
