package com.example.clothing_sell_website.service.customer.impl;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.service.customer.CustomerCusService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.clothing_sell_website.repository.CustomerRepository;

@org.springframework.stereotype.Service
public class CustomerCusServiceImpl implements CustomerCusService {
    @Autowired
    private CustomerRepository customerRepo;
    @Override
    public Customer getCustomerById(String customerId){
        return customerRepo
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại!"));
    }
}
