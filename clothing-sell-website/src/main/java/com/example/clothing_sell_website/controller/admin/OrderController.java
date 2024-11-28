package com.example.clothing_sell_website.controller.admin;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.Order;
import com.example.clothing_sell_website.service.admin.AccountService;
import com.example.clothing_sell_website.service.admin.BillService;
import com.example.clothing_sell_website.service.admin.OrderService;
import com.example.clothing_sell_website.service.admin.impl.StaffService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;
    StaffService staffService;
    BillService billService;
    AccountService accountService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping
    public String getOrders(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("orderList", orderService.getOrders());
        return "admin/order";
    }

    @GetMapping("/update-order/{id}")
    public String updateProduct(@PathVariable Integer id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        //        model.addAttribute("order", orderService.findOrderById(id));
        //        model.addAttribute("staffList", staffService.getStaffs());
        Order order = orderService.findOrderById(id);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //        System.out.println("Tên account " + username);
        //        Account account = accountService.getAccountByUsername(username);

        order.setStatus(true);
        //        order.setStaff(staffService.getStaffById());
        orderService.save(order);

        billService.save(Bill.builder()
                .totalAmount(billService.getTotalAmount(id))
                .date(LocalDateTime.now())
                .build());
        return "redirect:/admin/order";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("order") Order order,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            orderService.save(order);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "success");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Đơn hàng đã được cập nhật thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "error");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Đơn hàng này không thể cập nhật.");
        }
        return "redirect:/admin/order";
    }

    @GetMapping("/order-list/{id}")
    public String getOrderLists(@PathVariable Integer id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("orderListList", orderService.getOrderListsByOrderId(id));
        return "admin/order-list";
    }
}
