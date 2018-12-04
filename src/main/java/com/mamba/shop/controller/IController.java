package com.mamba.shop.controller;

import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Period;
import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.RegistrationService;
import com.mamba.shop.service.ShopService;
import com.mamba.shop.service.impl.IShopDetailsService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Controller
public class IController {

    private final DownloadFile downloadFile;
    private final ShopService shopService;
    private final RegistrationService registrationService;

    @Autowired
    public IController(DownloadFile downloadFile,
                       ShopService shopService,
                       RegistrationService registrationService
    ) {
        this.downloadFile = downloadFile;
        this.shopService = shopService;
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

    @GetMapping("/contacts")
    public String getContact(){
        return "contact";
    }

    @RequestMapping("/get.download/jsd1134is6chd_uhc_sid/sdcs32dvg2222112/{id}5987412365/bzs123fff_gbc)ss")
    public String downloadCalendar(@PathVariable String id, HttpServletResponse response){
        System.out.println("Скачиваем календарь номер = " + id);
        Apartment apartment = shopService.getByIdWithDependency(id);
        Set<Period> periods =  apartment.getPeriods();
        downloadFile.writeCalendar("room234" + id + ".ics",periods,"Europe/Minsk");
        downloadFile.downloadCalendar(id, response);
        return "index";
    }
    @GetMapping(value = "/404")
    public String notFoundPage(){
        return "_404";
    }

    @GetMapping(value = "/singUp")
    public String singUpForm(){
        return "registrationForm";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String singUp(
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password1", defaultValue = "") String password1,
            @RequestParam(value = "password2", defaultValue = "") String password2,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "phone", defaultValue = "") String phone,
            @RequestParam(value = "service_code", defaultValue = "") String service_cod,
            Model model
    ){

        if (password1.equals(password2)){
            try{
                registrationService.regUser(username, password1, email, phone, service_cod);
                model.addAttribute("registrationSuccess", true);
            }
            catch (IllegalAccessException ex){
                model.addAttribute("verificationError", true);
                //model.addAttribute("registrationSuccess", false);
            } catch (IllegalArgumentException e) {
                model.addAttribute("duplicate_user", true);
            }
        }
        return "login";
    }
}
