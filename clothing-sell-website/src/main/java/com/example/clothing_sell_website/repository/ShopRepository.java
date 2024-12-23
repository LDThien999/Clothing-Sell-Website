package com.example.clothing_sell_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Product;

@Repository
public interface ShopRepository extends JpaRepository<Product, String> {
    @Modifying
    @Query("FROM Product p WHERE p.brand.brandId = :brandId ")
    List<Product> getProductByBrand(@Param("brandId") String brandId);

    @Modifying
    @Query("FROM Product p WHERE p.type.typeId = :typeId ")
    List<Product> getProductByType(@Param("typeId") String typeId);
}
