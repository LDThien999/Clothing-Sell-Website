package com.example.clothing_sell_website.service.admin.impl;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.repository.BillRepository;
import com.example.clothing_sell_website.repository.CustomerRepository;
import com.example.clothing_sell_website.service.admin.BillService;
import com.example.clothing_sell_website.service.admin.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
