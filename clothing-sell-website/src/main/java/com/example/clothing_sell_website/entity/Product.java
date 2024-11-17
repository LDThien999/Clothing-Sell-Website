package com.example.clothing_sell_website.entity;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "ProductId")
    String productId;

    @Column(name = "ProductName")
    String productName;

    @Column(name = "Price")
    Float price;

    @Column(name = "Size", length = 1)
    String size;

    @Column(name = "Origin")
    String origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BrandId")
    Brand brand;

    @Column(name = "InventoryQuantity")
    Integer inventoryQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TypeId")
    Type type;
}
