package com.mamba.shop.controller;


import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


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
                                   @RequestParam(value = "bedroom", defaultValue = "2") String bedroom){
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
        int countDay = shopService.getCountOrderDay(dateIn, dateOut);
        model.addAttribute("countDay", countDay);
        model.addAttribute("apartmentList",
        shopService.searchFreeApartmentsWithDependency(
                countPeople, countChild,
                district, priceMax,
                dateIn, dateOut, bedroom)
        );
        return "searchPage";
    }
    @RequestMapping("/order")
    public String order(){
        
        return "";
    }

    @RequestMapping("/about")
    public String getAbout(){
        return "about";
    }

    @RequestMapping("/contacts")
    public String getContact(){
        return "contact";
    }

    @RequestMapping("/get.download/jsd1134is6chd_uhc_sid/sdcs32dvg2222112/{id}5987412365/bzs123fff_gbc)ss")
    public String downloadCalendar(@PathVariable String id, HttpServletResponse response){
        System.out.println("Here_____" + id);
        FileInputStream file = null;
        try {
            file = new FileInputStream("D:\\calendar\\room1.ics");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/calendar");

        response.setHeader("Content-Disposition","attachment; filename=\"" + "Room123ewwxxxxxx" + id + ".ics" +"\"");
        try {
            assert file != null;
            FileCopyUtils.copy(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
