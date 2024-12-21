package com.example.clothing_sell_website.service.customer;
import com.example.clothing_sell_website.entity.Cart;
import java.util.List;



public interface CartService {
    Cart saveCart(Cart cart);
    void deleteCart(long cartId);
    Cart getCartByCP(String customerId, String productId);
    List <Cart> getCartByCus(String customerId);
    Cart getCartById(long cartId);
}

