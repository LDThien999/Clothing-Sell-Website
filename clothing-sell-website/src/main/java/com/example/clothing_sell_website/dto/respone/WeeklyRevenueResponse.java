package com.example.clothing_sell_website.dto.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeeklyRevenueResponse {
    int dayOfWeek;
    double revenue;
}
