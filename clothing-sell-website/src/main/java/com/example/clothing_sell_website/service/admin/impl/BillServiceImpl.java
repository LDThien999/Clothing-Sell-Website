package com.example.clothing_sell_website.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.repository.BillRepository;
import com.example.clothing_sell_website.service.admin.BillService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillServiceImpl implements BillService {
    BillRepository billRepository;

    @Override
    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Integer id) {
        return billRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Không tìm thấy hóa đơn với id là " + id));
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }
}
