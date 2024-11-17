package com.example.clothing_sell_website.service.admin.impl;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.repository.ProductRepository;
import com.example.clothing_sell_website.service.admin.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tồn tại sản phẩm với id là " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tồn tại sản phẩm với id là " + id));
        productRepository.deleteById(id);
    }
}
