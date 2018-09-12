package com.mamba.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ILoginController {

    @RequestMapping("/")
    public String getLoginPage(){
        return "login";
    }
}
