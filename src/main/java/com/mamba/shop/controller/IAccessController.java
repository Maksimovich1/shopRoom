package com.mamba.shop.controller;


import com.mamba.shop.entity.*;
import com.mamba.shop.entity.custom_entity.CustomPricesModel;
import com.mamba.shop.entity.custom_entity.PaymentCustomModel;
import com.mamba.shop.entity.custom_entity.PeriodLongCustomModel;
import com.mamba.shop.service.MailService;
import com.mamba.shop.service.RegistrationService;
import com.mamba.shop.service.ShopService;
import com.mamba.shop.service.impl.IShopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/secure")
public class IAccessController {

    private final ShopService shopService;
    private final MailService mailService;
    private final RegistrationService userServiceReg;

    @Value(value = "${app.controllerAccess.adminEmail}")
    private String emailAdmin;
    @Value(value = "${app.controllerAccess}")
    private String pathImage;

    @Autowired
    public IAccessController(ShopService shopService,
                             MailService mailService,
                             RegistrationService userServiceReg) {
        this.shopService = shopService;
        this.mailService = mailService;
        this.userServiceReg = userServiceReg;
    }

    @RequestMapping("/product")
    public String product(
            Model model,
            HttpServletRequest request
    ){
        model.addAttribute("view", false);
        request.getSession().setAttribute("balans", shopService.getCurrentUser().getCash());
        request.getSession().setAttribute("paymentMethod", 1); // покупка по залогу
        return "searchPage";
    }

    @RequestMapping(value = {"/productList"}, method = RequestMethod.GET)
    public String getSearchProduct(Model model,
                                   @RequestParam(value = "countP", defaultValue = "2") String countPeople,
                                   @RequestParam(value = "countC", defaultValue = "0")String countChild,
                                   @RequestParam(value = "district", defaultValue = "A")String district,
                                   @RequestParam(value = "priceMax", defaultValue = "100")String priceMax,
                                   @RequestParam(value = "dateIn", defaultValue = "")String dateIn,
                                   @RequestParam(value = "dateOut", defaultValue = "")String dateOut,
                                   @RequestParam(value = "bedroom", defaultValue = "2") String bedroom,
                                   HttpServletRequest request
    ){
        Map<String, List<CustomPricesModel>> mapListPrices = new HashMap<>(); // для разных цен
        try {
            List<Apartment> apartments = shopService.searchFreeApartmentsWithDependency(
                    countPeople, countChild,
                    district, priceMax,
                    dateIn, dateOut, bedroom);
            for (Apartment ap: apartments
                    ) {
                int priceNew = 0;
                List<CustomPricesModel> customPricesModelList =
                        shopService.setPriceForThePeriod(ap, dateIn, dateOut);
                for (CustomPricesModel cpm :
                        customPricesModelList) {
                    priceNew += Integer.valueOf(cpm.getPrice());
                    System.out.println(cpm.getPrice());
                }
                priceNew = priceNew / customPricesModelList.size();
                mapListPrices.put(ap.getId(), customPricesModelList); // оптимизировать изменить на апартамент а не id
                ap.setPrice(priceNew);
            }
            model.addAttribute("apartmentList", apartments);
            request.getSession().setAttribute("mapListPrice", mapListPrices);
            request.getSession().setAttribute("apartmentList", apartments);
            int countDay = shopService.getCountOrderDay(dateIn, dateOut);
            model.addAttribute("countDay", countDay);
            model.addAttribute("price", priceMax);
        }
        catch (ParseException e) {
            e.printStackTrace();
            model.addAttribute("invalidDate", true);
            return "searchPage";
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            model.addAttribute("invalidNumber", true);
            return "searchPage";
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("view", true);
        return "searchPage";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/product/apartment/{id}")
    public String getViewApartmentPage(
            @PathVariable String id,
            @RequestParam(value = "dateIn", defaultValue = "") String dateIn,
            @RequestParam(value = "dateOut", defaultValue = "") String dateOut,
            Model model,
            HttpServletRequest request
    ){
        Map<String, List<CustomPricesModel>> mapListPrices;
        mapListPrices = (Map<String, List<CustomPricesModel>>) request.getSession().getAttribute("mapListPrice");
        List<CustomPricesModel> pricesModelList =
                mapListPrices.get(id);

        if (dateIn.equals("") || dateOut.equals(""))
            return "redirect:product";

        // сбор данных для отображения детальной информации апартамента
        /*Apartment apartment = shopService.getById(id);*/
        List<Apartment> apartments = (List<Apartment>) request.getSession().getAttribute("apartmentList");
        // проверяем апартамент на новую цену
        Apartment apartment = null;
        for (Apartment ap :
                apartments) {
            if (ap.getId().equals(id))
                apartment = ap;
        }
        // если не найден достаем из базы по стандартной цене
        if (apartment == null)
            apartment = shopService.getById(id);

        request.getSession().setAttribute("apartment", apartment);

        String username = shopService.getCurrentUser().getUsername();
        int countOrderDay = shopService.getCountOrderDay(dateIn, dateOut);
        String summary = String.valueOf(countOrderDay * apartment.getPrice());

        int percent = apartment.getPrice() * apartment.getPercent_of_price() / 100 * countOrderDay;

        PaymentCustomModel paymentCustomModel =
                new PaymentCustomModel(id, dateIn, dateOut,
                        countOrderDay, percent, summary, shopService.getNameDistrict(apartment.getDistrict()),username);
        request.getSession().setAttribute("paymentCustomModel", paymentCustomModel);
        model.addAttribute(apartment);
        model.addAttribute(paymentCustomModel);
        model.addAttribute("pricesModelList",pricesModelList);
        model.addAttribute("percent", percent);
        if (Integer.parseInt(summary) <= shopService.getCurrentUser().getCash())
            model.addAttribute("checkFullPayment", true);
        if (percent <= shopService.getCurrentUser().getCash())
            model.addAttribute("checkCreditPayment", true);

        if (shopService.hasThePriceChanged(pricesModelList))
            model.addAttribute("oldPrice", shopService.getById(id).getPrice());

        return "viewApartment";
    }
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/quickOrder", method = RequestMethod.POST)
    public String quickOrder(
            @RequestParam(value = "idApartment") String idApartment,
            @RequestParam(value = "dateIn") String dateIn,
            @RequestParam(value = "dateOut") String dateOut,
            HttpServletRequest request,
            Model model
    ){

        Apartment thisApartment = null;

        List<Apartment> apartments = (List<Apartment>) request.getSession().getAttribute("apartmentList");
        for (Apartment ap :
                apartments) {
            if (ap.getId().equals(idApartment)) {
                thisApartment = ap;
            }
        }
        if (thisApartment == null) throw new NullPointerException("Данного апартамента нет в сессии");

        User user = shopService.getCurrentUser();
        int countOrderDay = shopService.getCountOrderDay(dateIn, dateOut);
        int percent = thisApartment.getPrice() * thisApartment.getPercent_of_price() / 100 * countOrderDay;
        String summary = String.valueOf(countOrderDay * thisApartment.getPrice());
        PaymentCustomModel paymentCustomModel =
                new PaymentCustomModel(idApartment, dateIn, dateOut,
                        countOrderDay,
                        percent, summary, shopService.getNameDistrict(thisApartment.getDistrict()),user.getUsername());
        request.getSession().setAttribute("paymentCustomModel", paymentCustomModel);
        String dateNow = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).format(new Date());

        model.addAttribute("paymentCustomModel", paymentCustomModel);
        model.addAttribute("dateNow", dateNow);
        model.addAttribute("apartment", thisApartment);
        model.addAttribute("email", user.getEmail());
        return "confirmationPage";
    }

    @RequestMapping("/order")
    public String callbackOrder(){
        return "redirect:product";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/order"}, method = RequestMethod.POST)
    public String order(
            @RequestParam(value = "idApartment") String idApartment,
            @RequestParam(value = "dateIn") String dateIn,
            @RequestParam(value = "dateOut") String dateOut,
            @RequestParam(value = "paymentMethod", defaultValue = "1") String paymentMethod,
            Model model,
            HttpServletRequest request
    ){
        request.getSession().setAttribute("paymentMethod", paymentMethod);
        Apartment apartment = (Apartment) request.getSession().getAttribute("apartment");
        String dateNow = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).format(new Date());

        User user = shopService.getCurrentUser();
        PaymentCustomModel paymentCustomModel = (PaymentCustomModel) request.getSession().getAttribute("paymentCustomModel");

        if (paymentMethod.equals("2"))
            paymentCustomModel.setPledge(Integer.parseInt(paymentCustomModel.getSummary()));
        // передача параметров на сторону клиента для составления чека
        model.addAttribute("paymentCustomModel", paymentCustomModel);
        model.addAttribute("dateNow", dateNow);
        model.addAttribute("apartment", apartment);
        model.addAttribute("email", user.getEmail());

            /*period.setDate_in(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateIn));
            period.setDate_out(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateOut));*/
        return "confirmationPage";
    }
    @RequestMapping(value = "/complete")
    public String callbackCompleteOrder(){
        return "redirect:product";
    }
    /*
    * 1 - покупка успешно создана
    * 2 - апартамент был занят другим покупателем
    * 3 - ошибка отправки email
    * 4 - неизвестная ошибка
    * */
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String completeOrder(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "apartment") String apartmentId,
            @RequestParam(value = "dateIn") String dateIn,
            @RequestParam(value = "dateOut") String dateOut,
            Model model,
            HttpServletRequest request
    ){
        long milisec_15_hour = 54000000;
        String paymentMethodString = String.valueOf(request.getSession().getAttribute("paymentMethod"));
        int paymentMethod = Integer.parseInt(paymentMethodString);
        User user = shopService.getCurrentUser();
        PaymentCustomModel paymentCustomModel = (PaymentCustomModel) request.getSession().getAttribute("paymentCustomModel");
        if (!paymentCustomModel.getDateIn().equals(dateIn) ||
                !paymentCustomModel.getDateOut().equals(dateOut) ||
                !paymentCustomModel.getApartmentName().equals(apartmentId))
            throw new IllegalArgumentException("Не совпадает сессия с параметрами");

        try {
            // переводим в date даты пришедшие с jsp
            long dateInsideMs = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateIn).getTime();
            long dateOutsideMs = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateOut).getTime();
            //прибавляем 15 часов для корректной записи в бд
            Date dateInside = new Date(dateInsideMs + milisec_15_hour);
            Date dateOutside = new Date(dateOutsideMs + milisec_15_hour);
            // проверяем на наличие этого периода в бд
            if (shopService.checkFreeApartmentForDate(apartmentId,
                    new Date(dateInsideMs), new Date(dateOutsideMs)) ){
                // если он уникальный true то создаем ордер на покупку
                Orders order = shopService.createOrder(email, user.getUsername(),
                        new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).format(new Date()),
                        dateInside, dateOutside, apartmentId, paymentMethod,
                        paymentCustomModel.getSummary());
                order.setPledge(paymentCustomModel.getPledge());

                Period period = new Period();
                period.setDate_in(dateInside);
                period.setDate_out(dateOutside);

                // передаем созданный ордер на запись в бд
                // проверяем позволяет ли баланс совершить бронь
                int mid = user.getCash() - paymentCustomModel.getPledge();
                if (mid < 0) throw new IllegalArgumentException("Не достаточно средств");
                user.setCash(mid);

                shopService.setCompleteOrder(order, apartmentId, period, user.getUsername());
                userServiceReg.updateUser(user);
                request.getSession().setAttribute("balans", shopService.getCurrentUser().getCash());
                // отправка писем
                mailService.sendEmail(order, email, null, 1);
                // все прошло успешно

                model.addAttribute("createNewOrder", 1);
            }
            else{
                model.addAttribute("createNewOrder", 2);
                return "successOrder";
            }
        }
        catch (ClassCastException e){
            model.addAttribute("createNewOrder", 3);
            e.printStackTrace();
            return "successOrder";
        }
        catch (MessagingException e){
            model.addAttribute("createNewOrder", 3);
            return "successOrder";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("createNewOrder", 5);
            return "successOrder";
        }
        catch (Exception ex){
            model.addAttribute("createNewOrder", 4);
            return "successOrder";
        }
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
    @RequestMapping("/confirmPaymentUser")
    public String confirmPaymentUser(
            @RequestParam(value = "id") String idOrder,
            @RequestParam(value = "idApartment") String idApartment,
            @RequestParam(value = "status") String status,
            Model model,
            HttpServletRequest request
    ){
        String role = "user"; // права у пользователя на этом контролере
            int st = Integer.valueOf(status);
            if (st == -1){
                // пользователь отменяет бронь самостоятельно
                int pen9 = shopService.deleteOrder(idOrder, role);
                User user = shopService.getCurrentUser();
                user.setCash(user.getCash() + pen9);
                userServiceReg.updateUser(user);
                request.getSession().setAttribute("balans", shopService.getCurrentUser().getCash());
                return "orderPage";
            }
            if (st == 2) {
                // попытка оплатить полностью
                /*mailService.sendEmail(null, emailAdmin, idOrder, 2);//письмо админу о оплате
                model.addAttribute("message", "Администрация проверит вашу оплату в ближайшее время");*/
            }
        return "orderPage";
    }

    @RequestMapping("/getImage/{photo}")
    @ResponseBody
    public byte[] getImage(
            @PathVariable String photo
    ){
        byte[] imageInByte = new byte[0];
        try {

            String path = shopService.getPicture(Integer.parseInt(photo)).getPict();
            imageInByte = shopService.getImageForApartment(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageInByte;
    }
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage(){
        return "_404";
    }
}