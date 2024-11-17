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
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "CustomerId")
    String customerId;

    @Column(name = "Name")
    String name;

    @Column(name = "PhoneNum")
    String phoneNum;

    @Column(name = "Email")
    String email;

    @Column(name = "CreditNum")
    String creditNum;
}
