package com.example.clothing_sell_website.controller.user;

import com.example.clothing_sell_website.entity.*;
import com.example.clothing_sell_website.repository.ShopRepository;
import com.example.clothing_sell_website.service.admin.AccountService;
import com.example.clothing_sell_website.service.customer.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ShoppingController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CustomerCusService customerService;
    @Autowired
    private OrderListService orderListService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LevelService lvService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ShopRepository shopRepo;

    @GetMapping("/shop.html")
    public String shop(Model model, HttpServletRequest request) {
        List<Product> products = shopService.getAllProduct();
        List<Type> types = typeService.getAllType();
        List<Brand> brands = brandService.getAllBrand();
        String username = (String) request.getSession().getAttribute("currentCustomer");
        System.out.println(username);
        Customer cus = accountService.getAccountById(username).getCustomer();
        //Customer cus = customerService.getCustomerById("KH001");
        model.addAttribute("customer",cus);
        model.addAttribute("products", products);
        model.addAttribute("types", types);
        model.addAttribute("brands", brands);
        return "user/shopping/shop";
    }

    @GetMapping("/shop/brand/{brandId}")
    public String shopBrand(@PathVariable String brandId, Model model) {
        List<Product> products = shopService.getProductByBrand(brandId);
        model.addAttribute("products", products);
        List<Type> types = typeService.getAllType();
        List<Brand> brands = brandService.getAllBrand();
        model.addAttribute("types", types);
        model.addAttribute("brands", brands);
        return "user/shopping/shop";

    }

    @GetMapping("/shop/type/{typeId}")
    public String shopType(@PathVariable String typeId, Model model) {
        List<Product> products = shopService.getProductByType(typeId);
        model.addAttribute("products", products);
        List<Type> types = typeService.getAllType();
        List<Brand> brands = brandService.getAllBrand();
        model.addAttribute("types", types);
        model.addAttribute("brands", brands);
        return "user/shopping/shop";

    }

    @GetMapping("/shop/detail/{productId}/{label}")
    public String shopDetail(@PathVariable("productId") String productId,@PathVariable("label") int label, Model model,HttpSession session
    ,HttpServletRequest request) {
        Product product = shopService.getProductById(productId);
        String username = (String) request.getSession().getAttribute("currentCustomer");
        Customer cus = accountService.getAccountById(username).getCustomer();
        model.addAttribute("customer",cus);
        model.addAttribute("product", product);
        List<Product> recommendProducts = shopService.getTop10Products(label);
        List<String> listRe = (List<String>) request.getSession().getAttribute("listRecomm");
        List<Product> listFinal = new ArrayList<>();
        if(!listRe.isEmpty()){
            List<Product> recommendProducts2 = shopRepo.findAllById(listRe);
            listFinal.addAll(recommendProducts2);
        }
        listFinal.addAll(recommendProducts);         // Thêm tất cả phần tử từ recommendProducts



        model.addAttribute("recommendProducts", listFinal);
        LevelOfInterest lv = lvService.getLVByCusPro(cus.getCustomerId(), productId);
        lv.setLabel(label);
        lvService.saveLV(lv);

        List<Review> reviews = reviewService.getReviewByPro(productId);
        // Xáo trộn danh sách
        Collections.shuffle(reviews);

        // Lấy 5 phần tử ngẫu nhiên
        List<Review> randomItems = reviews.size() > 3 ? reviews.subList(0, 3) : reviews;
        model.addAttribute("reviews", randomItems);
        return "user/shopping/shop-details";

    }

    @GetMapping("/checkout/{orderId}")
    public String checkout(@PathVariable int orderId, Model model){
        List<OrderList> orderLists = orderListService.getOrderListByOrder(orderId);
        Order order = orderLists.get(0).getOrder();
        model.addAttribute("orderLists", orderLists);
        model.addAttribute("order", order);
        return "user/shopping/checkout";
    }

}
