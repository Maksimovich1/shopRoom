package com.mamba.shop.controller;

import com.mamba.shop.dao.ProductDao;
import com.mamba.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping({"/productList"})
    public String getSearchProduct(Model model,
                                   @RequestParam(value = "countP", defaultValue = "2") String countPeople,
                                   @RequestParam(value = "countC", defaultValue = "0")String countChild,
                                   @RequestParam(value = "priceMin", defaultValue = "50")String priceMin,
                                   @RequestParam(value = "priceMax", defaultValue = "100")String priceMax,
                                   @RequestParam(value = "dateIn", defaultValue = "today")String dateIn,
                                   @RequestParam(value = "dateOut", defaultValue = "tomorrow")String dateOut){
        model.addAttribute("productList",
                //productDao.getAllProduct()
        null);
        return "searchPage";
    }
}
