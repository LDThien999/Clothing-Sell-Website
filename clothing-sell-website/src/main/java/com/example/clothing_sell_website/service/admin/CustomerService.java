package com.example.clothing_sell_website.service.admin;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public Customer getCustomerById(String id);

    public Customer save(Customer customer);
    //    public void delete(String id);
}
