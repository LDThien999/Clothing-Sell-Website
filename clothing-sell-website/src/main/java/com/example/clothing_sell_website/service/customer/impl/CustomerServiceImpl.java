package com.example.clothing_sell_website.service.customer.impl;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Type;
import com.example.clothing_sell_website.repository.TypeRepository;
import com.example.clothing_sell_website.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.clothing_sell_website.repository.CustomerRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepo;
    @Override
    public Customer getCustomerById(String customerId){
        return customerRepo
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại!"));
    }
}
