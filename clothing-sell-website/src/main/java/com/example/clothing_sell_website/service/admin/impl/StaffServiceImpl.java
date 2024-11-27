package com.example.clothing_sell_website.service.admin.impl;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.Staff;
import com.example.clothing_sell_website.repository.BillRepository;
import com.example.clothing_sell_website.repository.StaffRepository;
import com.example.clothing_sell_website.service.admin.BillService;
import com.example.clothing_sell_website.service.admin.StaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffServiceImpl implements StaffService {
    StaffRepository staffRepository;

    @Override
    public List<Staff> getStaffs() {
        return staffRepository.findAll();
    }
}
