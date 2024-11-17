package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {
}
