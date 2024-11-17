package com.example.clothing_sell_website.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Type")
public class Type {
    @Id
    @Column(name = "TypeId")
    String typeId;

    @Column(name = "TypeName")
    String typeName;
}
