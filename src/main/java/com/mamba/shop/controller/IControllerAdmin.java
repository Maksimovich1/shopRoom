package com.mamba.shop.controller;

import com.mamba.shop.entity.Apartment;
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
    public String updateDB(){
        return "updatePage";
    }

    @RequestMapping(value = {"/addApartment"}, method = RequestMethod.GET)
    public String addApartment(
            @RequestParam(value = "id1", defaultValue = "") String id,
            @RequestParam(value = "bedroom", defaultValue = "") String bedroom,
            @RequestParam(value = "people", defaultValue = "") String people,
            @RequestParam(value = "children", defaultValue = "") String children,
            @RequestParam(value = "price", defaultValue = "") String price,
            @RequestParam(value = "district", defaultValue = "") String district,
            @RequestParam(value = "about", defaultValue = "") String about,
            Model model
    ){
        Apartment apartment = new Apartment();
        apartment.setId(id);
        apartment.setBedroom(Integer.parseInt(bedroom));
        apartment.setPeople(Integer.parseInt(people));
        apartment.setChildren(Integer.parseInt(children));
        apartment.setPrice(Integer.parseInt(price));
        apartment.setDistrict(Integer.parseInt(district));
        apartment.setAbout(about);
        apartment.setEnable(1);
        shopService.addApartment(apartment);
        model.addAttribute("addStatus", true );
        System.out.println(id + ": " + about);
        return "redirect:updateOrAdd";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") String id,
                         Model model){
                shopService.deleteApartment(id);
        System.out.println("####" + id);
        model.addAttribute("delete", true);
        return "redirect:control";
    }
    @RequestMapping(value = "/searchForId")
    public String searchForId(
            @RequestParam(value = "ida", defaultValue = "") String id,
            Model model
    ){
        Apartment apartment = shopService.getByIdWithDependency(id);
        model.addAttribute("apartment", apartment);
        System.out.println("### " + apartment.getId());
        return "updatePage";
    }

}
