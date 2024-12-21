package com.example.clothing_sell_website.controller.user;
import com.example.clothing_sell_website.entity.*;
import com.example.clothing_sell_website.service.admin.ProductService;
import com.example.clothing_sell_website.service.customer.CartService;
import com.example.clothing_sell_website.service.customer.CustomerCusService;
import com.example.clothing_sell_website.service.customer.OrderCusService;
import com.example.clothing_sell_website.service.customer.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController

public class CartWork{
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerCusService cusService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderCusService orderService;
    @Autowired
    private OrderListService orderListService;
    @PostMapping("/deleteCart")
    public ResponseEntity<?> deleteData(@RequestBody Map<String, Long> data) {
        long cartId = data.get("cartId");
        cartService.deleteCart(cartId);

        return ResponseEntity.ok(Map.of("message", "Giỏ hàng đã được xóa"));
    }

    @PostMapping("/addCart")
    public ResponseEntity<?> addData(@RequestBody Map<String, String> data) {
        String productId = data.get("productId");
        int quantity = Integer.parseInt(data.get("quantity"));
        Cart cartTest = cartService.getCartByCP("KH001",productId);
        if(cartTest != null) {
            cartTest.setQuantity(cartTest.getQuantity()+quantity);
            cartService.saveCart(cartTest);
        }
        else{
            Cart cart = new Cart();
            cart.setCustomer(cusService.getCustomerById("KH001"));
            cart.setProduct(productService.getProductById(productId));
            cart.setQuantity(quantity);
            cart.setStatus(true);
            cartService.saveCart(cart);
        }

        return ResponseEntity.ok(Map.of("message", "Đã thêm vào giỏ hàng"));
    }

    @PostMapping("/order")
    public ResponseEntity<?> orderDt(@RequestBody Map<String, Object> data) {
        List<Map<String, Object>> objectList = (List<Map<String, Object>>) data.get("objectList");
        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();

        order.setDate(now);
        order.setPaymentMethod("Tiền mặt");
        order.setStatus(false);
        order.setCustomer(cusService.getCustomerById("KH001"));
        orderService.saveOrder(order);
        for (Map<String, Object> item : objectList) {
            long cartId = Long.parseLong((String) item.get("cartId"));
            int quantity = Integer.parseInt((String) item.get("quantity"));
            System.out.println(cartId);
            Cart cart = cartService.getCartById(cartId);
            if(cart.getQuantity() <= quantity) {
                cart.setQuantity(0);
                cartService.saveCart(cart);
            }
            else{
                cart.setQuantity(cart.getQuantity()-quantity);
                cartService.saveCart(cart);
            }
            OrderList orderList = new OrderList();
            orderList.setCart(cart);
            orderList.setOrder(order);
            orderList.setQuantity(quantity);
            orderListService.saveOrderList(orderList);
        }

        return ResponseEntity.ok(Map.of("message", order.getOrderId()));
    }

}
