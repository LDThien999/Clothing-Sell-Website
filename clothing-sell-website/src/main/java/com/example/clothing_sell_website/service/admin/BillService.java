package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Bill;

public interface BillService {
    List<Bill> getBills();

    Bill getBillById(Integer id);

    Bill save(Bill bill);

    Float getTotalAmount(Integer orderId);
    //    public void delete(String id);
}
