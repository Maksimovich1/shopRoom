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

    @RequestMapping(value = {"/updateOrAdd"}, method = RequestMethod.GET)
    public String update(
            @RequestParam(value = "id1", defaultValue = "") String id,
            @RequestParam(value = "bedroom", defaultValue = "") String bedroom,
            @RequestParam(value = "people", defaultValue = "") String people,
            @RequestParam(value = "children", defaultValue = "") String children,
            @RequestParam(value = "price", defaultValue = "") String price,
            @RequestParam(value = "district", defaultValue = "") String district,
            @RequestParam(value = "about", defaultValue = "") String about,
            Model model
    ){
        System.out.println(id + ": " + about);
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
