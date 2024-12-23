package com.example.clothing_sell_website.controller.user;
import com.example.clothing_sell_website.entity.*;
import com.example.clothing_sell_website.service.admin.AccountService;
import com.example.clothing_sell_website.service.admin.ProductService;
import com.example.clothing_sell_website.service.customer.*;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private LevelService lvService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/deleteCart")
    public ResponseEntity<?> deleteData(@RequestBody Map<String, Long> data) {
        long cartId = data.get("cartId");
        cartService.deleteCart(cartId);

        return ResponseEntity.ok(Map.of("message", "Giỏ hàng đã được xóa"));
    }

    @PostMapping("/addCart")
    public ResponseEntity<?> addData(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String productId = data.get("productId");
        int quantity = Integer.parseInt(data.get("quantity"));
        String username = (String) request.getSession().getAttribute("currentCustomer");
        Customer cus = accountService.getAccountById(username).getCustomer();
        Cart cartTest = cartService.getCartByCP(cus.getCustomerId(),productId);
        if(cartTest != null) {
            cartTest.setQuantity(cartTest.getQuantity()+quantity);
            cartService.saveCart(cartTest);
        }
        else{
            Cart cart = new Cart();
            cart.setCustomer(cus);
            cart.setProduct(productService.getProductById(productId));
            cart.setQuantity(quantity);
            cart.setStatus(true);
            cartService.saveCart(cart);
        }
        LevelOfInterest lv = lvService.getLVByCusPro(cus.getCustomerId(), productId);
        if(lv == null){
            lv = new LevelOfInterest();
            lv.setCustomer(cus);
            lv.setProduct(productService.getProductById(productId));
            lv.setLevelInt(5);
            lvService.saveLV(lv);
        }else{
            lv.setLevelInt(lv.getLevelInt() + 5);
            lvService.saveLV(lv);
        }

        return ResponseEntity.ok(Map.of("message", "Đã thêm vào giỏ hàng"));
    }

    @PostMapping("/order")
    public ResponseEntity<?> orderDt(@RequestBody Map<String, Object> data, HttpServletRequest request) {
        List<Map<String, Object>> objectList = (List<Map<String, Object>>) data.get("objectList");
        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();

        order.setDate(now);
        order.setPaymentMethod("Tiền mặt");
        order.setStatus(false);
        String username = (String) request.getSession().getAttribute("currentCustomer");
        Customer cus = accountService.getAccountById(username).getCustomer();
        order.setCustomer(cus);
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

    @PostMapping("/increaseLevel/{customerId}/{productId}")
    public ResponseEntity<?> getRecommendData(@RequestBody Map<String, String> data,@PathVariable("customerId") String customerId
    , @PathVariable("productId") String productId) {
        Customer customer = cusService.getCustomerById(customerId);
        Product product = productService.getProductById(productId);

        try {
            LevelOfInterest lv = lvService.getLVByCusPro(customerId, productId);

            if (lv == null) {
                lv = new LevelOfInterest();
                lv.setCustomer(customer);
                lv.setProduct(product);
                lv.setLevelInt(3);
                lvService.saveLV(lv);
            } else {
                lv.setLevelInt(lv.getLevelInt() + 1);
                lvService.saveLV(lv);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error updating LevelOfInterest", e);
        }
        return ResponseEntity.ok(Map.of("message","tương tác thành công "));
    }

    @PostMapping("/pushCmt")
    public ResponseEntity<?> deleteData(@RequestBody Map<String, Object> data, HttpServletRequest request) {
        String productId = (String) data.get("productId");
        String comment = (String) data.get("comment");
        int score = Integer.parseInt((String) data.get("score"));
        LocalDateTime currentTime = LocalDateTime.now();
        String username = (String) request.getSession().getAttribute("currentCustomer");
        Customer cus = accountService.getAccountById(username).getCustomer();
        try{
            Review review = reviewService.getReviewByProCus(productId,cus.getCustomerId());
            if(review == null){
                review = new Review();
                review.setCustomer(cus);
                review.setProduct(productService.getProductById(productId));
                review.setComment(comment);
                review.setScore(score);
                review.setDate(currentTime);
                reviewService.saveReview(review);
            }
            else{
                review.setComment(comment);
                review.setScore(score);
                review.setDate(currentTime);
                reviewService.saveReview(review);
            }
        }catch(RuntimeException e){
            throw new RuntimeException("Error updating Comment", e);
        }


        return ResponseEntity.ok(Map.of("message", "Bình luận của bạn đã được đăng lên!"));
    }

    @PostMapping("/getDataAPI")
    public ResponseEntity<?> getDataAPI(@RequestBody Map<String, Object> data, HttpServletRequest request) {
        List<String> listRe = (List<String>) data.get("items");
        request.getSession().setAttribute("listRecomm",listRe);

        return ResponseEntity.ok(Map.of("message", "Giỏ hàng đã được xóa"));
    }

}
