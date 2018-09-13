package com.mamba.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ISecureController {

    @RequestMapping("/secure/search")
    public String getProduct(){
        return "searchPageSecure";
    }

}
