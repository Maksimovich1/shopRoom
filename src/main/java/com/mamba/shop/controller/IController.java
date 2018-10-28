package com.mamba.shop.controller;

import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.impl.IShopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class IController {

    private final DownloadFile downloadFile;

    @Autowired
    public IController(DownloadFile downloadFile) {
        this.downloadFile = downloadFile;
    }

    @GetMapping("/")
    public String index(){
        return "index";
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
        System.out.println("Скачиваем календарь номер = " + id);
        downloadFile.downloadCalendar(id, response);
        return "index";
    }
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage(){
        return "_404";
    }

}
