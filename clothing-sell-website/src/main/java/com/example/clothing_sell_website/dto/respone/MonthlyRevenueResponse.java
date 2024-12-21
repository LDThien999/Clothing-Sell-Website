package com.example.clothing_sell_website.dto.respone;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Staff;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyRevenueResponse {
    int month;
    double revenue;
    long customerCount;
}
