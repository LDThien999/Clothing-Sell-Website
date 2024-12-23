package com.example.clothing_sell_website.controller.user;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping({"/", "/index.html"})
    public String index(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("currentCustomer");
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
    public String profile() {
        return "user/profile";
    }
}
