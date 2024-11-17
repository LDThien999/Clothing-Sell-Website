package com.example.clothing_sell_website.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum NotificationType {
    SUCCESS("success"),
    ERROR("error"),
    WARNING("warning"),
    INFO("info"),
    ;
    String message;
}
