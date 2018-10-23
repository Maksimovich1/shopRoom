package com.mamba.shop.controller;


import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Period;
import com.mamba.shop.entity.User;
import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.ShopService;
import com.mamba.shop.service.impl.IShopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/secure")
public class IAccessController {

    private final ShopService shopService;
    private final DownloadFile downloadFile;

    private int countNight = 0;
    private String dateIn = "";
    private String dateOut = "";
    private int summary = 0;
    private Period period = null;

    @Autowired
    public IAccessController(ShopService shopService, DownloadFile downloadFile) {
        this.shopService = shopService;
        this.downloadFile = downloadFile;
    }

    @RequestMapping("/product")
    public String product(){
        return "searchPage";
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
        countNight = countDay;
       this.dateIn = dateIn;
       this.dateOut = dateOut;
       period = new Period();
        try {
            period.setDate_in(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateIn));
            period.setDate_out(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateOut));
            period.getDate_in().setHours(15);
            period.getDate_out().setHours(15);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
    public String callbackOrder(){
        to_zero();
        return "redirect:product";
    }
    @RequestMapping(value = {"/order"}, method = RequestMethod.POST)
    public String order(
            @RequestParam(value = "idApartment") String idApartment,
            Model model
    ){
        int id = Integer.parseInt(idApartment);
        Apartment apartment = shopService.getById(idApartment);
        model.addAttribute("id", id);
        model.addAttribute("countNight", countNight);

        String date_str = dateIn + " " + dateOut;
        Period period = new Period();
        String dateNow = "";
        try {
            period.setDate_in(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateIn));
            period.setDate_out(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateOut));
            dateNow = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).format(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //передача параметров на составление чека операции
        model.addAttribute("period_str", date_str);
        model.addAttribute("dateNow", dateNow);
        model.addAttribute("period", period);
        summary = countNight * apartment.getPrice();
        model.addAttribute("apartment", apartment);
        model.addAttribute("summary", summary);
        return "confirmationPage";
    }
    @RequestMapping(value = "/complete")
    public String callbackCompleteOrder(){
        to_zero();
                return "redirect:product";
    }
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String completeOrder(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "apartment") String apartmentId,
            Model model
    ){
        String username = shopService.getCurrentUser().getUsername();


        try {

            downloadFile.writeCalendar("\\room234"+ apartmentId + ".ics", period,
                    username, "Europe/Moscow");

            Apartment apartment = shopService.getByIdWithDependency(apartmentId);
            apartment.getPeriods().add(period);
            shopService.updateApartment(apartment);
            shopService.createOrder(email, username,
                    new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).format(new Date()),
                    period.getDate_in(), period.getDate_out(), apartmentId, 0,
                    String.valueOf(summary));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("id", shopService.getOrderByUsername(username));
        return "successOrder";
    }
    @RequestMapping("/my_order")
    public String my_order(
            Model model
    ){
        User user = shopService.getCurrentUser();
        if (user != null)
        model.addAttribute("orders", shopService.getOrderByUsername(user.getUsername()));
        return "orderPage";
    }
    private void to_zero(){
        countNight = 0;
        dateIn = "";
        dateOut = "";
        summary = 0;
        period = null;
    }
}