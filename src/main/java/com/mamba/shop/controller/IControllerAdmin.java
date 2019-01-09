package com.mamba.shop.controller;

import com.mamba.shop.entity.*;
import com.mamba.shop.entity.custom_entity.dto.UserDto;
import com.mamba.shop.service.MailService;
import com.mamba.shop.service.OrdersService;
import com.mamba.shop.service.RegistrationService;
import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class IControllerAdmin {

    private final ShopService shopService;
    private final OrdersService ordersService;
    private final MailService mailService;
    private final RegistrationService userServiceReg;

    @Value(value = "${app.controllerAccess}")
    private String pathImage;

    @Autowired
    public IControllerAdmin(ShopService shopService, OrdersService ordersService,
                            MailService mailService, RegistrationService userServiceReg) {
        this.shopService = shopService;
        this.ordersService = ordersService;
        this.mailService = mailService;
        this.userServiceReg = userServiceReg;
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
            @RequestParam(value = "bathroom", defaultValue = "0") String bathroom,
            @RequestParam(value = "size", defaultValue = "0") String size,
            @RequestParam(value = "address", defaultValue = "") String address,
            @RequestParam(value = "nameApar", defaultValue = "apartment") String nameApar,
            @RequestParam(value = "nameManager", defaultValue = "") String nameManager,
            @RequestParam(value = "phoneManager", defaultValue = "") String phoneManager,
            @RequestParam(value = "emailManager", defaultValue = "") String emailManager,
            @RequestParam(value = "checkIn", defaultValue = "12") String checkIn,
            @RequestParam(value = "checkOut", defaultValue = "12") String checkOut,
            @RequestParam(value = "possessions", defaultValue = "0") String possessions,
            @RequestParam(value = "discount", defaultValue = "") String discount,
            @RequestParam(value = "percent", defaultValue = "") String percent,
            @RequestParam(value = "bed1", defaultValue = "") String bed1,
            @RequestParam(value = "bed2", defaultValue = "") String bed2,
            @RequestParam(value = "namepartner", defaultValue = "0") String namepartner,
            @RequestParam(value = "district", defaultValue = "") String district,
            @RequestParam(value = "about", defaultValue = "") String about,
            @RequestParam(value = "urlbooking", defaultValue = "") String url,
            @RequestParam(value = "lat", defaultValue = "0") String lat,
            @RequestParam(value = "lng", defaultValue = "0") String lng,
            @RequestParam(value = "wifi", defaultValue = "0") String wifi,
            @RequestParam(value = "pool", defaultValue = "0") String pool,
            @RequestParam(value = "heated_pool", defaultValue = "0") String heated_pool,
            @RequestParam(value = "animal", defaultValue = "0") String animal,
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
        apartment.setUrlBooking(url);
        apartment.setEnable(1);
        apartment.setLatitude(lat);
        apartment.setLongitude(lng);
        apartment.setSize_room(Integer.parseInt(size));
        apartment.setBedroom(Integer.parseInt(bathroom));
        apartment.setAddress(address);
        apartment.setNameApartment(nameApar);
        apartment.setNameManager(nameManager);
        apartment.setPhoneManager(phoneManager);
        apartment.setEmailManager(emailManager);
        apartment.setCheck_in(checkIn);
        apartment.setCheck_out(checkOut);
        apartment.setPossessions(Integer.parseInt(possessions));
        apartment.setDiscount(Integer.parseInt(discount));
        apartment.setPercent_of_price(Integer.parseInt(percent));
        apartment.setBed1(Integer.parseInt(bed1));
        apartment.setBed1(Integer.parseInt(bed2));
        apartment.setName_partner(Integer.parseInt(namepartner));

        apartment.setWifi(wifi.equals("1"));
        apartment.setPool(pool.equals("1"));
        apartment.setHeated_pool(heated_pool.equals("1"));
        apartment.setAnimal(animal.equals("1"));
        try{
            shopService.addApartment(apartment);
            model.addAttribute("addStatus", true );
            System.out.println(id + ": " + about);
        }
        catch (IllegalArgumentException ex){
            ex.printStackTrace();
            model.addAttribute("addStatus", false );
            model.addAttribute("message", "Апартамент с таким id существует" );
            return "updatePage";
        }
        return "updatePage";
    }

    @RequestMapping(value = "/searchForId")
    public String searchForId(
            @RequestParam(value = "ida", defaultValue = "") String id,
            Model model
    ){
        Apartment apartment = shopService.getByIdWithDependency(id);
        Set<Picture> pictures = null;
        if (apartment != null)
        pictures = apartment.getPictures();
        model.addAttribute("pictureList", pictures);
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
            @RequestParam(value = "bathroom", defaultValue = "0") String bathroom,
            @RequestParam(value = "size", defaultValue = "0") String size,
            @RequestParam(value = "address", defaultValue = "") String address,
            @RequestParam(value = "nameApar", defaultValue = "apartment") String nameApar,
            @RequestParam(value = "nameManager", defaultValue = "") String nameManager,
            @RequestParam(value = "phoneManager", defaultValue = "") String phoneManager,
            @RequestParam(value = "emailManager", defaultValue = "") String emailManager,
            @RequestParam(value = "checkIn", defaultValue = "12") String checkIn,
            @RequestParam(value = "checkOut", defaultValue = "12") String checkOut,
            @RequestParam(value = "possessions", defaultValue = "0") String possessions,
            @RequestParam(value = "discount", defaultValue = "") String discount,
            @RequestParam(value = "percent", defaultValue = "") String percent,
            @RequestParam(value = "bed1", defaultValue = "") String bed1,
            @RequestParam(value = "bed2", defaultValue = "") String bed2,
            @RequestParam(value = "namepartner", defaultValue = "0") String namepartner,
            @RequestParam(value = "district", defaultValue = "") String district,
            @RequestParam(value = "about", defaultValue = "") String about,
            @RequestParam(value = "urlbooking", defaultValue = "") String url,
            @RequestParam(value = "lat", defaultValue = "0") String lat,
            @RequestParam(value = "lng", defaultValue = "0") String lng,
            @RequestParam(value = "wifi", defaultValue = "0") String wifi,
            @RequestParam(value = "pool", defaultValue = "0") String pool,
            @RequestParam(value = "heated_pool", defaultValue = "0") String heated_pool,
            @RequestParam(value = "animal", defaultValue = "0") String animal,
            Model model
    ){

        Apartment apartment = shopService.getById(id);
        apartment.setBedroom(Integer.parseInt(bedroom));
        apartment.setPeople(Integer.parseInt(people));
        apartment.setChildren(Integer.parseInt(children));
        apartment.setPrice(Integer.parseInt(price));
        apartment.setDistrict(Integer.parseInt(district));
        apartment.setAbout(about);
        apartment.setUrlBooking(url);
        apartment.setEnable(1);
        apartment.setLatitude(lat);
        apartment.setLongitude(lng);
        apartment.setSize_room(Integer.parseInt(size));
        apartment.setBathroom(Integer.parseInt(bathroom));
        apartment.setAddress(address);
        apartment.setNameApartment(nameApar);
        apartment.setNameManager(nameManager);
        apartment.setPhoneManager(phoneManager);
        apartment.setEmailManager(emailManager);
        apartment.setCheck_in(checkIn);
        apartment.setCheck_out(checkOut);
        apartment.setPossessions(Integer.parseInt(possessions));
        apartment.setDiscount(Integer.parseInt(discount));
        apartment.setPercent_of_price(Integer.parseInt(percent));
        apartment.setBed1(Integer.parseInt(bed1));
        apartment.setBed1(Integer.parseInt(bed2));
        apartment.setName_partner(Integer.parseInt(namepartner));
        apartment.setWifi(wifi.equals("1"));
        apartment.setPool(pool.equals("1"));
        apartment.setHeated_pool(heated_pool.equals("1"));
        apartment.setAnimal(animal.equals("1"));

        shopService.updateApartment(apartment);

        model.addAttribute("message", "Апартамент был изменен" );
        return "updatePage";
    }

    /*
    * Конец блока добавление и обновления апартаментов
    * */
    //--------------------------------------
    /*
    * Начало блока работа с ордерами
    * */

    /*//Переход в ордера null значит не выгружать из бд данные
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String openOrdersMainPage(Model model){
        //Date date = new Date();
        //model.addAttribute("orders", shopService.getAllOrdersForDate(date));
        model.addAttribute("orders", null);
        model.addAttribute("userNameList", userServiceReg.getAllUser());
        return "orderPage";
    }*/
    // поиск по передаваемым параметрам
    @RequestMapping(value = "/orders")
    public String getOrdersByParam(
            @RequestParam(value = "idOrder", defaultValue = "") String idOrder,
            @RequestParam(value = "status", defaultValue = "0") String status,
            @RequestParam(value = "idRoom", defaultValue = "") String idRoom,
            @RequestParam(value = "username", defaultValue = "") String username,
            Model model){

        List<Orders> ordersList = ordersService.getOrdersByParameter(idOrder, status, idRoom, username);
        model.addAttribute("userNameList", userServiceReg.getAllUser());
        model.addAttribute("orders", ordersList);
        return "orderPage";
    }

    // подтверждение оплат и удаление ордеров
    @RequestMapping(value = "/confirmPaymentUser", method = RequestMethod.POST)
    public String confirmPaymentUser(
            @RequestParam String id,
            @RequestParam String status
    ){
        String role = "admin";
        int st = Integer.valueOf(status);
        if (st == -1){
            Orders order = shopService.getOrderById(id);
            User user = shopService.getUserByUserName(order.getCustomer_name());
            int pen9 = shopService.deleteOrder(id, role);
            user.setCash(user.getCash() + pen9);
            userServiceReg.updateUser(user);
        }
        return "redirect:orders";
    }
    /*
    * Конец работы с ордерами
    * */
    /*
    * Блокировка дат админом и добавление акций
    * */
    @RequestMapping(value = "/blocks")
    public String blockApartmentDate(Model model){
        model.addAttribute("apartmentList", shopService.getAllApartments());
        model.addAttribute("alert", null);
        return "searchPageSecure";
    }
    @ResponseBody
    @RequestMapping(value = "/getBlockForApart", method = RequestMethod.POST)
    public Set<Period> getAllBlocksAdminThisApart(
            @RequestBody String idApartment
    ){
        Apartment apartment = shopService.getByIdWithDependency(idApartment);
        System.out.println("Ajax works_______");
        return apartment.getPeriods();
    }

    @RequestMapping(value = "/createNewPrice")
    public String createNewPrice(
            @RequestParam(value = "idApar") String idApartament,
            @RequestParam(value = "newPeriodIn") String dateBegin,
            @RequestParam(value = "newPeriodOut") String dateEnd,
            @RequestParam(value = "namePeriod") String namePeriod,
            @RequestParam(value = "newPrice") String price,
            Model model
    ){
        try {
            shopService.addNewPrice(namePeriod, dateBegin, dateEnd, price, idApartament);
            model.addAttribute("alert", "Успешно добалено");
        } catch (ParseException e) {
            e.printStackTrace();
            model.addAttribute("alert", "Ошибка на сервере, не удалось преодразовать дату");
        }
        return "searchPageSecure";
    }
    /*
    * Конец блокировки дат админом и добавление акций
    **/
    /*
    * Блок работы с юзерами, отключение и просмотр
    * */
    @RequestMapping(value = "/users")
    public String manageUsers(){
        return "";
    }

    @RequestMapping(value = "/save_image", method = RequestMethod.POST )
    public String uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idApart") String idApartment,
            HttpServletRequest request
            ) throws IOException, ServletException {

        Random random = new Random();
        //System.out.println(request.getRealPath(imageDir));
        String path = "resources\\images\\" + idApartment + "\\" + "room" + random.nextInt() + ".jpg";
        shopService.saveImageForApartment(file, idApartment, path);
        return "redirect:searchForId?ida=" + idApartment;
    }

    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public String deleteImage(
            @RequestParam(value = "idImage", defaultValue = "-1") String idImage,
            @RequestParam(value = "idApart") String idApart
    ){
        shopService.deletePicture(Integer.parseInt(idImage));
        return "redirect:searchForId?ida=" + idApart;
    }

    @RequestMapping(value = "/userControl", method = RequestMethod.GET)
    public String userControl(
            Model model
    ){
        List<User> userList = userServiceReg.getAllUser();
        model.addAttribute("userList", userList);
        return "pageUser";
    }
    @ResponseBody
    @RequestMapping("/getUserByName")
    public UserDto getAllUserJson(
            @RequestBody String username
    ){
        User user = userServiceReg.getUserByUsernameWithDependency(username);
        UserDto userDto = new UserDto(user);
        List<Orders> orderByUsername = shopService.getOrderByUsername(username);
        String mass [] = user.getAuthorities().stream().map(Authorities::getAuthority).toArray(String[]::new);
        userDto.setRoleInSystem(mass[0]);
        userDto.setOrderAmount(String.valueOf(orderByUsername.stream().mapToInt(Orders::getPrice).sum())); //

        int debt = 0;
        for (Orders o :
                orderByUsername) {
            debt += o.getPrice() - o.getPledge();
        }
        userDto.setSummaryOfDebt(String.valueOf(debt));

        return userDto;
    }

    /*
    * Redirect to 404 page
    * */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage(){
        return "_404";
    }

}
