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
@Table(name = "Brand")
public class Brand {
    @Id
    @Column(name = "BrandId")
    String brandId;

    @Column(name = "BrandName")
    String brandName;
}
