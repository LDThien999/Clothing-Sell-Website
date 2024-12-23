package com.example.clothing_sell_website.controller.user;

import com.example.clothing_sell_website.entity.Product;
import com.example.clothing_sell_website.service.customer.ShopService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Test {
    @Autowired
    ShopService shopService;
    @GetMapping({"/", "/index.html"})
    public String index(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("currentCustomer");
        List<Product> hotProductsViet = shopService.getHotProductsByVietNam().stream()
                .limit(4)
                .collect(Collectors.toList());
        List<Product> hotProductsJP = shopService.getHotProductsByJP().stream()
                .limit(4)
                .collect(Collectors.toList());

        model.addAttribute("hotProductsVi", hotProductsViet);
        model.addAttribute("hotProductsJP", hotProductsJP);
        return "user/index";
    }

    @GetMapping("/about.html")
    public String about() {
        return "user/about";
    }

    @GetMapping("/blog.html")
    public String blog() {
        return "user/blog";
    }

    @GetMapping("/blog-details.html")
    public String blogDetails() {
        return "user/blog-details";
    }

    @GetMapping("/contact.html")
    public String contact() {
        return "user/contact";
    }

    @GetMapping("/login.html")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/register.html")
    public String registerPage() {
        return "user/register";
    }

    @GetMapping("/user-information.html")
    public String profile() {return "user/profile";}

    @GetMapping("/user/orders.html")
    public String orders() {return "user/my-order-list";}

}
