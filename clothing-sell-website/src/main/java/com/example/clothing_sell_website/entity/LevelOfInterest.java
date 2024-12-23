package com.example.clothing_sell_website.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "LevelOfInterest")
public class LevelOfInterest {
    @Id
    @Column(name = "LevelId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer LevelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId")
    Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    Product product;

    @Column(name = "LevelInt")
    Integer levelInt;

    @Column(name = "LabelMain")
    Integer label;
}
