package com.example.clothing_sell_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {}
