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
@Table(name = "Cart")
public class Cart {
    @Id
    @Column(name = "CartId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    Product product;

    @Column(name = "Quantity")
    Integer quantity;

    @Column(name = "Status")
    Boolean status;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    Customer customer;
}
