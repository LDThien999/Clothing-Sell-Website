package com.example.clothing_sell_website.service.admin;

import java.util.List;

import com.example.clothing_sell_website.entity.Staff;

public interface StaffService {
    List<Staff> getStaffs();

    Staff getStaffById(String id);
    //    public Type save(Type type);
    //    public void delete(String id);
}
