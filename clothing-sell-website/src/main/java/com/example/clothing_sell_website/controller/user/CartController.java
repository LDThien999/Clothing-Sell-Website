package com.example.clothing_sell_website.controller.user;
import com.example.clothing_sell_website.entity.Cart;
import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.admin.ProductService;
import com.example.clothing_sell_website.service.customer.CartService;
import com.example.clothing_sell_website.service.customer.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService cusService;
    @Autowired
    private ProductService productService;

    @GetMapping("/shop/addCart/{productId}/{quantity}/{customerId}")
    public String addCart(@PathVariable String productId, @PathVariable int quantity, @PathVariable String customerId, Model model) {
        Cart cartTest = cartService.getCartByCP(customerId,productId);
        if(cartTest != null) {
            cartTest.setQuantity(cartTest.getQuantity()+quantity);
            cartService.saveCart(cartTest);
        }
        else{
            Cart cart = new Cart();
            cart.setCustomer(cusService.getCustomerById(customerId));
            cart.setProduct(productService.getProductById(productId));
            cart.setQuantity(quantity);
            cart.setStatus(true);
            cartService.saveCart(cart);
        }
        return "redirect:/shop.html";
    }

    @GetMapping("/cart.html")
    public String cart(Model model) {
        List <Cart> carts = cartService.getCartByCus("KH001");
        model.addAttribute("carts", carts);
        return "user/Shopping/shopping-cart";
    }

}
