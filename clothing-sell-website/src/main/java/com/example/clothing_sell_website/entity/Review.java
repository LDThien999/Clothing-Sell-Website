package com.example.clothing_sell_website.entity;

import java.time.LocalDateTime;

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
@Table(name = "Review")
public class Review {
    @Id
    @Column(name = "ReviewId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId")
    Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    Product product;

    @Column(name = "Score")
    Integer score;

    @Column(name = "Comment")
    String comment;

    @Column(name = "Date")
    LocalDateTime date;
}
