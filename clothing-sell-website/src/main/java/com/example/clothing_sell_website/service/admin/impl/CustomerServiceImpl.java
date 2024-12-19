package com.example.clothing_sell_website.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.repository.CustomerRepository;
import com.example.clothing_sell_website.service.admin.CustomerService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với id là " + id));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
