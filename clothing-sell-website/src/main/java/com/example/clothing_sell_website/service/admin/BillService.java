package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Bill;

public interface BillService {
    public List<Bill> getBills();
        public Bill getBillById(Integer id);
        public Bill save(Bill bill);
    //    public void delete(String id);
}
