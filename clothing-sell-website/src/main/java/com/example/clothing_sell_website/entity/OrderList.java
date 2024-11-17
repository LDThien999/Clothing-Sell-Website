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
@Table(name = "OrderList")
public class OrderList {
    @Id
    @Column(name = "OrderListId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CartId")
    Cart cart;

    @Column(name = "Quantity")
    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BillCode")
    Bill bill;
}
