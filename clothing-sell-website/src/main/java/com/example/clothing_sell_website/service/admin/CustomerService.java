package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Customer;

public interface CustomerService {
    List<Customer> getCustomers();

    Customer getCustomerById(String id);

    Customer save(Customer customer);
    //    public void delete(String id);
}
