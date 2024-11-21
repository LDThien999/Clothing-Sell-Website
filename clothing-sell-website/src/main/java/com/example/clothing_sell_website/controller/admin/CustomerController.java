package com.example.clothing_sell_website.controller.admin;

import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.admin.BillService;
import com.example.clothing_sell_website.service.admin.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/customer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping
    public String getOrders(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("customerList", customerService.getCustomers());
        return "admin/customer";
    }

    @GetMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable String id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "admin/update-customer";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("customer") Customer customer,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            customerService.save(customer);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "success");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Khách hàng đã được cập nhật thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "error");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Khách hàng này không thể cập nhật.");
        }
        return "redirect:/admin/customer";
    }
}
