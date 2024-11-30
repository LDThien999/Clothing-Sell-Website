package com.example.clothing_sell_website.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.service.admin.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/admin/api/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderRestController {
    OrderService orderService;

    @GetMapping("/filter")
    public ResponseEntity<List<Order>> filterOrders(@RequestParam String status) {
        List<Order> orders;
        switch (status) {
            case "all":
                orders = orderService.getOrders();
                break;
            case "delivered":
                orders = orderService.getOrdersByStatus(true);
                break;
            case "pending":
                orders = orderService.getOrdersByStatus(false);
                break;
            default:
                orders = orderService.getOrders();
        }
        return ResponseEntity.ok(orders);
    }
}
