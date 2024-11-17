package com.example.clothing_sell_website.controller.admin;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.admin.BrandService;
import com.example.clothing_sell_website.service.admin.OrderService;
import com.example.clothing_sell_website.service.admin.ProductService;
import com.example.clothing_sell_website.service.admin.TypeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping
    public String getOrders(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("orderList", orderService.getOrders());
        return "admin/order";
    }

}
