package com.example.clothing_sell_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

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
