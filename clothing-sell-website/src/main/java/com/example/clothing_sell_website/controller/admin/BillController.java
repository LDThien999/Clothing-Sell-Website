package com.example.clothing_sell_website.controller.admin;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.clothing_sell_website.dto.respone.BillPrintResponse;
import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.entity.OrderList;
import com.example.clothing_sell_website.service.admin.BillService;
import com.example.clothing_sell_website.service.admin.OrderService;
import com.example.clothing_sell_website.service.admin.StaffService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequestMapping("/admin/bill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
    BillService billService;
    OrderService orderService;
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

    @GetMapping("/bill-list/{id}")
    public String getOrderLists(@PathVariable Integer id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("orderListList", orderService.getOrderListsByOrderId(id));
        return "admin/bill-list";
    }

    @GetMapping("/api/bills/{id}")
    @ResponseBody
    public BillPrintResponse getBillDetails(@PathVariable Integer id) {
        Bill bill = billService.getBillById(id);
        List<OrderList> orderLists =
                orderService.getOrderListsByOrderId(bill.getOrder().getOrderId());

        BillPrintResponse printResponse = BillPrintResponse.builder()
                .billCode(bill.getBillCode())
                .date(bill.getDate())
                .totalAmount(bill.getTotalAmount())
                .staff(bill.getStaff())
                .customer(orderLists.get(0).getCart().getCustomer())
                .build();

        List<BillPrintResponse.OrderListResponse> details = orderLists.stream()
                .map(orderList -> BillPrintResponse.OrderListResponse.builder()
                        .productName(orderList.getCart().getProduct().getProductName())
                        .quantity(orderList.getQuantity())
                        .price(orderList.getCart().getProduct().getPrice())
                        .build())
                .toList();
        printResponse.setOrderListResponses(details);
        return printResponse;
    }
}
