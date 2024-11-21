package com.example.clothing_sell_website.controller.admin;

import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.Customer;
import com.example.clothing_sell_website.service.admin.StaffService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_sell_website.service.admin.BillService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/bill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
    BillService billService;
    StaffService staffService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping
    public String getBills(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("billList", billService.getBills());
        return "admin/bill";
    }

    @GetMapping("/update-bill/{id}")
    public String updateBill(@PathVariable Integer id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("bill", billService.getBillById(id));
        model.addAttribute("staffList", staffService.getStaffs());
        return "admin/update-bill";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("bill") Bill bill,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            billService.save(bill);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "success");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Hóa đơn đã được cập nhật thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "error");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Hóa đơn này không thể cập nhật.");
        }
        return "redirect:/admin/bill";
    }
}
