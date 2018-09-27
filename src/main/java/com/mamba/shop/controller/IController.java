package com.mamba.shop.controller;

import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = {"/productList"}, method = RequestMethod.GET)
    public String getSearchProduct(Model model,
                                   @RequestParam(value = "countP", defaultValue = "2") String countPeople,
                                   @RequestParam(value = "countC", defaultValue = "0")String countChild,
                                   @RequestParam(value = "district", defaultValue = "A")String district,
                                   @RequestParam(value = "priceMax", defaultValue = "100")String priceMax,
                                   @RequestParam(value = "dateIn", defaultValue = "2018-09-20")String dateIn,
                                   @RequestParam(value = "dateOut", defaultValue = "2018-09-22")String dateOut,
                                   @RequestParam(value = "bedroom", defaultValue = "1") String bedroom){
        switch (district) {
            case "A":
                district = "1";
                break;
            case "B":
                district = "2";
                break;
            case "C":
                district = "3";
                break;
        }
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
