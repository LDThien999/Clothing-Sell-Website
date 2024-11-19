package com.example.clothing_sell_website.service.customer.impl;

import com.example.clothing_sell_website.entity.Cart;
import com.example.clothing_sell_website.service.customer.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.clothing_sell_website.repository.CartRepository;
import java.util.List;

@org.springframework.stereotype.Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepo;
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepo
                .findById(cartId);
        cartRepo.deleteById(cartId);
    }

    @Override
    public Cart getCartByCP(String customerId, String productId) {
        return cartRepo.getCartByCP(customerId, productId);
    }

    @Override
    public List<Cart> getCartByCus(String customerId){
        return cartRepo.getCartByCus(customerId);
    }


}
