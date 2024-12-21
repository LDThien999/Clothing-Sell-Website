package com.example.clothing_sell_website.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Bill")
public class Bill {
    @Id
    @Column(name = "BillCode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer billCode;

    @Column(name = "TotalAmount")
    Float totalAmount;

    @Column(name = "Date")
    LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffId")
    Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order")
    Order order;
}
