package com.example.clothing_sell_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.clothing_sell_website.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {
}
