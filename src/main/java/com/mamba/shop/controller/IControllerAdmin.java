package com.mamba.shop.controller;

import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Picture;
import com.mamba.shop.service.ShopService;
import com.mamba.shop.service.impl.IShopDetailsService;
import com.mamba.shop.service.uploadFile.FileBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        return "updatePage";
    }

    @RequestMapping(value = "/update")
    public String updateApartment(
            @RequestParam(value = "ida", defaultValue = "") String id,
            @RequestParam(value = "bedroom", defaultValue = "") String bedroom,
            @RequestParam(value = "people", defaultValue = "") String people,
            @RequestParam(value = "children", defaultValue = "") String children,
            @RequestParam(value = "price", defaultValue = "") String price,
            @RequestParam(value = "district", defaultValue = "") String district,
            @RequestParam(value = "about", defaultValue = "") String about,
            Model model
    ){
                Apartment apartment = shopService.getById(id);
                apartment.setBedroom(Integer.parseInt(bedroom));
                apartment.setPeople(Integer.parseInt(people));
                apartment.setChildren(Integer.parseInt(children));
                apartment.setPrice(Integer.parseInt(price));
                apartment.setDistrict(Integer.parseInt(district));
                apartment.setAbout(about);
                shopService.updateApartment(apartment);
        return "redirect:updateOrAdd";
    }

    @RequestMapping(value = "/getUrl", method = RequestMethod.GET)
    public String getUrlCalendar(
            @RequestParam(value = "id", defaultValue = "") String id
    ){
                if (id.equals(""))
                    return "redirect:control";
                shopService.setBufferUrlBooking(id);
                return "redirect:control";
    }
    @RequestMapping(value = "/orders")
    public String getAllOrders(Model model){
        Date date = new Date();
        model.addAttribute("orders", shopService.getAllOrdersForDate(date));
        return "orderPage";
    }

    @RequestMapping("/confirmPaymentUser")
    public String confirmPaymentUser(
            @RequestParam String id,
            @RequestParam String status
    ){
        int st = Integer.valueOf(status);
        if (st == -1){
            shopService.deleteOrder(id);
        }else
        shopService.confirmOrderPaymentStatus(id, st);
        return "redirect:orders";
    }

    //не работает(
    @RequestMapping(value = "/save_image", method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(

            @RequestParam(value = "file") MultipartFile file1,
            @RequestParam(value = "file") CommonsMultipartFile[] file,
            HttpServletRequest request) throws IOException, ServletException {
        String id = "1";
        request.getPart("file");
                shopService.saveImageForApartment(file, id);
       return "redirect:control";
    }

}
