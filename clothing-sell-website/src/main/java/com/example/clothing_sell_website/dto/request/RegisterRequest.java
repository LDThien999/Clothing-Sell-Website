package com.example.clothing_sell_website.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String phoneNum;
    private String email;
    private String creditNum;
    private String username;
    private String password;
}