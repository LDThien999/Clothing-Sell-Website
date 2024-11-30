package com.example.clothing_sell_website.controller.admin;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_sell_website.service.admin.AccountService;
import com.example.clothing_sell_website.service.admin.BillService;
import com.example.clothing_sell_website.service.admin.OrderService;
import com.example.clothing_sell_website.service.admin.StaffService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequestMapping("/admin/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    OrderService orderService;
    StaffService staffService;
    BillService billService;
    AccountService accountService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping("/staff-account")
    public String getStaffAccounts(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("staffList", orderService.getOrders());
        return "admin/bill-register";
    }

    @GetMapping("/create-account")
    public String registerPage(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        //        model.addAttribute("orderList", orderService.getOrders());
        return "admin/page-register";
    }
}
