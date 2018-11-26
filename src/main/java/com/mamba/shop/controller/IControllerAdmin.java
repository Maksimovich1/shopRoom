package com.mamba.shop.controller;

import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Orders;
import com.mamba.shop.service.OrdersService;
import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class IControllerAdmin {

    private final ShopService shopService;
    private final OrdersService ordersService;

    @Autowired
    public IControllerAdmin(ShopService shopService, OrdersService ordersService) {
        this.shopService = shopService;
        this.ordersService = ordersService;
    }

    /*
    * Переход на удаление и получение ссылки
    * Начало блока -->
    * */
    @RequestMapping("/control")
    public String admin(Model model) {
        model.addAttribute("apartmentList", shopService.getAllApartments());
        return "adminPage";
    }

    //удаление по ID
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") String id,
                         Model model){
        shopService.deleteApartment(id);
        System.out.println("####" + id);
        model.addAttribute("delete", true);
        return "redirect:control";
    }

    //Получение ссылки в буфер
    @RequestMapping(value = "/getUrl", method = RequestMethod.GET)
    public String getUrlCalendar(
            @RequestParam(value = "id", defaultValue = "") String id,
            Model model
    ){
        if (id.equals(""))
            return "redirect:control";
        model.addAttribute("url_download", shopService.setBufferUrlBooking(id));
        return "adminPage";
    }
    /*
    * Конец блока удаление и получения ссылки
    * */
    //--------------------------------------------------------
    /*
    * Добавление и редактирование апартамента
    *
    * Начало блока -->
    * */
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
        System.out.println(about + "________________________");
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
            @RequestParam(value = "about", defaultValue = "") String about
            /*Model model*/
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

    /*
    * Конец блока добавление и обновления апартаментов
    * */
    //--------------------------------------
    /*
    * Начало блока работа с ордерами
    * */

    //Переход в ордера null значит не выгружать из бд данные
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String openOrdersMainPage(Model model){
        //Date date = new Date();
        //model.addAttribute("orders", shopService.getAllOrdersForDate(date));
        model.addAttribute("orders", null);
        return "orderPage";
    }
    // поиск по передаваемым параметрам
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String getOrdersByParam(
            @RequestParam(value = "idOrder", defaultValue = "") String idOrder,
            @RequestParam(value = "status", defaultValue = "0") String status,
            @RequestParam(value = "idRoom", defaultValue = "") String idRoom,
            @RequestParam(value = "username", defaultValue = "") String username,
            Model model){

        List<Orders> ordersList = ordersService.getOrdersByParameter(idOrder, status, idRoom, username);
        model.addAttribute("orders", ordersList);
        return "orderPage";
    }

    // подтверждение оплат и удаление ордеров
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
    /*
    * Конец работы с ордерами
    * */
    /*
    * Блокировка дат админом
    * */
    @RequestMapping(value = "/blocks")
    public String blockApartmentDate(){
        return "searchPageSecure";
    }
    /*
    * Блок работы с юзерами, отключение и просмотр
    * */
    @RequestMapping(value = "/users")
    public String manageUsers(){
        return "";
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

    /*
    * Redirect to 404 page
    * */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage(){
        return "_404";
    }

}
