package com.mamba.shop.controller;

import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;

@Controller
public class IController {

    private final ShopService shopService;

    @Autowired
    public IController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping({"/productList"})
    public String getSearchProduct(Model model,
                                   @RequestParam(value = "countP", defaultValue = "2") String countPeople,
                                   @RequestParam(value = "countC", defaultValue = "0")String countChild,
                                   @RequestParam(value = "district", defaultValue = "0")String district,
                                   @RequestParam(value = "priceMax", defaultValue = "100")String priceMax,
                                   @RequestParam(value = "dateIn", defaultValue = "today")String dateIn,
                                   @RequestParam(value = "dateOut", defaultValue = "tomorrow")String dateOut,
                                   @RequestParam(value = "bedroom", defaultValue = "1") String bedroom){
        model.addAttribute("apartmentList",
        shopService.searchFreeApartmentsWithDependency(
                countPeople, countChild,
                district, priceMax,
                dateIn, dateOut, bedroom)
        );
        return "searchPage";
    }

    @RequestMapping("/about")
    public String getAbout(){
        return "about";
    }

    @RequestMapping("/contacts")
    public String getContact(){
        return "contact";
    }
}
