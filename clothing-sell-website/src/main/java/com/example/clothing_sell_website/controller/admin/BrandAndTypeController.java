package com.example.clothing_sell_website.controller.admin;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.clothing_sell_website.entity.Brand;
import com.example.clothing_sell_website.entity.Type;
import com.example.clothing_sell_website.enums.NotificationType;
import com.example.clothing_sell_website.service.admin.BrandService;
import com.example.clothing_sell_website.service.admin.TypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequestMapping("/admin/brand-type")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandAndTypeController {
    BrandService brandService;
    TypeService typeService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping
    public String getBrandsAndTypes(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute(
                "brandList",
                brandService.getBrands().stream()
                        .peek(brand -> brand.setBrandId(brand.getBrandId().trim()))
                        .toList());
        model.addAttribute(
                "typeList",
                typeService.getTypes().stream()
                        .peek(type -> type.setTypeId(type.getTypeId().trim()))
                        .toList());
        return "admin/brand-type";
    }

    @GetMapping("/add-brand")
    public String addBrand(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("brand", new Brand());
        return "admin/add-brand";
    }

    @GetMapping("/add-type")
    public String addType(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("type", new Type());
        return "admin/add-type";
    }

    @GetMapping("/update-brand/{id}")
    public String updateBrand(@PathVariable String id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("brand", brandService.findBrandById(id));
        return "admin/update-brand";
    }

    @GetMapping("/update-type/{id}")
    public String updateType(@PathVariable String id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("type", typeService.findTypeById(id));
        return "admin/update-type";
    }

    @PostMapping("/save-brand")
    public String saveBrand(
            @ModelAttribute("brand") Brand brand,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            brandService.save(brand);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.SUCCESS.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Thương hiệu đã được thêm/cập nhật thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.ERROR.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Thương hiệu này không thể thêm/cập nhật.");
        }

        return "redirect:/admin/brand-type";
    }

    @PostMapping("/save-type")
    public String saveType(
            @ModelAttribute("type") Type type,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            typeService.save(type);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.SUCCESS.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Loại đã được thêm/cập nhật thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.ERROR.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Loại này không thể thêm/cập nhật.");
        }

        return "redirect:/admin/brand-type";
    }

    @GetMapping("/delete-brand/{id}")
    public String deleteBrand(
            @PathVariable String id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            brandService.delete(id);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.SUCCESS.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Thương hiệu đã được xóa thành công.");

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.ERROR.getMessage());
            redirectAttributes.addFlashAttribute(
                    NOTIFICATION_MESSAGE, "Thương hiệu này không thể xóa vì đã được sử dụng.");
        }
        return "redirect:/admin/brand-type";
    }

    @GetMapping("/delete-type/{id}")
    public String deleteType(
            @PathVariable String id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            typeService.delete(id);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.SUCCESS.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Loại đã được xóa thành công.");

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, NotificationType.ERROR.getMessage());
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Loại này không thể xóa vì đã được sử dụng.");
        }
        return "redirect:/admin/brand-type";
    }
}
