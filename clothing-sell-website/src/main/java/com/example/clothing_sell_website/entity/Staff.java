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
@Table(name = "Staff")
public class Staff {
    @Id
    @Column(name = "StaffId")
    String staffId;

    @Column(name = "Name")
    String name;

    @Column(name = "PhoneNum")
    String phoneNum;

    @Column(name = "Email")
    String email;

    @Column(name = "Sex")
    String sex;

    @Column(name = "Status")
    Boolean status;
}
