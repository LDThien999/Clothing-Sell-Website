package com.example.clothing_sell_website.controller.admin;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.admin.BrandService;
import com.example.clothing_sell_website.service.admin.ProductService;
import com.example.clothing_sell_website.service.admin.TypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    BrandService brandService;
    TypeService typeService;

    private static final String NOTIFICATION_TYPE = "notificationType";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    @GetMapping
    public String getProducts(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute(
                "productList",
                productService.getProducts().stream()
                        .peek(product ->
                                product.setProductId(product.getProductId().trim()))
                        .toList());
        return "admin/product";
    }

    @GetMapping("/add-product")
    public String addProduct(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("product", new Product());
        model.addAttribute("brandList", brandService.getBrands());
        model.addAttribute("typeList", typeService.getTypes());
        return "admin/add-product";
    }

    @GetMapping("/update-product/{id}")
    public String updateProduct(@PathVariable String id, HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("brandList", brandService.getBrands());
        model.addAttribute("typeList", typeService.getTypes());
        return "admin/update-product";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("product") Product product,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        product.setImage("product11.jpg");
        product.setTraffic(1);
        try {
            productService.save(product);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "success");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Sản phẩm đã được thêm/cập nhật thành công.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "error");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Sản phẩm này không thể thêm/cập nhật.");
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("currentUri", request.getRequestURI());
        try {
            productService.delete(id);
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "success");
            redirectAttributes.addFlashAttribute(NOTIFICATION_MESSAGE, "Sản phẩm đã được xóa thành công.");

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(NOTIFICATION_TYPE, "error");
            redirectAttributes.addFlashAttribute(
                    NOTIFICATION_MESSAGE, "Sản phẩm này không thể xóa vì đã được sử dụng.");
        }
        return "redirect:/admin/product";
    }
}
