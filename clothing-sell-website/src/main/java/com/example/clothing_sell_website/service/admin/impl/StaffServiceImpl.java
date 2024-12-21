package com.example.clothing_sell_website.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.entity.Staff;
import com.example.clothing_sell_website.repository.StaffRepository;
import com.example.clothing_sell_website.service.admin.StaffService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffServiceImpl implements StaffService {
    StaffRepository staffRepository;

    @Override
    public List<Staff> getStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(String id) {
        return staffRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với id là " + id));
    }
}
