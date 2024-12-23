package com.example.clothing_sell_website.controller.authentication;
import com.example.clothing_sell_website.dto.request.AuthenticationRequest;
import com.example.clothing_sell_website.dto.request.ChangePasswordRequest;
import com.example.clothing_sell_website.dto.request.RegisterRequest;
import com.example.clothing_sell_website.dto.request.UpdateCustomerRequest;
import com.example.clothing_sell_website.dto.respone.AuthenticationResponse;
import com.example.clothing_sell_website.dto.respone.RegisterResponse;

import com.example.clothing_sell_website.entity.Account;
import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.service.admin.AccountService;
import com.example.clothing_sell_website.service.admin.CustomerService;
import com.example.clothing_sell_website.service.admin.OrderService;
import com.example.clothing_sell_website.service.auth.AuthService;
import com.example.clothing_sell_website.service.auth.JwtService;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private OrderService orderService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateLogin(
            @RequestBody AuthenticationRequest request, HttpServletRequest httpRequest) {
        AuthenticationResponse response = authService.authenticate(request);
        if (Objects.equals(response.getRole(), "customer")) {
            httpRequest.getSession().setAttribute("currentCustomer", request.getUsername());
        } else if (Objects.equals(response.getRole(), "staff")) {
            httpRequest.getSession().setAttribute("currentStaff", request.getUsername());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerAccount(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerNewCustomerAccount(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register-staff")
    public ResponseEntity<RegisterResponse> registerStaffAccount(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerNewStaffAccount(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtService.isTokenValid(token)) {
                return ResponseEntity.ok("ok");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không tồn tại");
    }

    public void getAdminInfo() {}

    @GetMapping("/admin/info")
    private ResponseEntity<String> getStringResponseEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
    }

    @GetMapping("/api/user/profile")
    public ResponseEntity<Customer> profile(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String username =
                    SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = accountService.getAccountById(username).getCustomer();
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/user/order-list")
    public ResponseEntity<List<Order>> orderList(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = accountService.getAccountById(username).getCustomer();
            List<Order> orders = orderService.getOrdersByCustomer(customer);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/user/profile-update")
    public ResponseEntity<Customer> profileUpdate(
            @RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateCustomerRequest request) {
        try {
            String username =
                    SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = accountService.getAccountById(username).getCustomer();
            customer.setName(request.getName());
            customer.setEmail(request.getEmail());
            customer.setPhoneNum(request.getPhoneNum());
            customer.setCreditNum(request.getCreditNum());

            customerService.save(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/api/user/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestBody ChangePasswordRequest request) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Account account = accountService.getAccountByUsername(username);
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            account.setPassword(encodedPassword);

            accountService.save(account);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

