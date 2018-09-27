package com.mamba.shop.controller;

import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class IControllerAdmin {

    private final ShopService shopService;

    @Autowired
    public IControllerAdmin(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("/control")
    public String admin(Model model) {
        model.addAttribute("apartmentList", shopService.getAllApartments());
        return "adminPage";
    }

    @RequestMapping("/updateOrAdd")
    public String update(){
        return "updatePage";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") String id,
                         Model model){
        System.out.println("####" + id);
        model.addAttribute("delete", true);
        return "redirect:control";
    }

}
