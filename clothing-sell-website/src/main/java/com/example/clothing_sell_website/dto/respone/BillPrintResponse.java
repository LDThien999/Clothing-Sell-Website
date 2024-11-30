package com.example.clothing_sell_website.dto.respone;

import java.time.LocalDateTime;
import java.util.List;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Staff;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillPrintResponse {
    Integer billCode;
    LocalDateTime date;
    Float totalAmount;
    Staff staff;
    Customer customer;
    List<OrderListResponse> orderListResponses;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrderListResponse {
        String productName;
        Integer quantity;
        Float price;
    }
}
