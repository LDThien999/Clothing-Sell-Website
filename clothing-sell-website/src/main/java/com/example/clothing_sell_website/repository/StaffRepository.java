package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {}
