package com.mamba.shop.service;

import com.mamba.shop.entity.*;
import com.mamba.shop.entity.custom_entity.CustomPricesModel;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ShopService {

    List<Apartment> searchFreeApartmentsWithDependency(
            String countPeople, String countChild,
            String district, String priceMax,
            String dateIn, String dateOut, String bedroom
    ) throws ParseException, NumberFormatException;
    List<Apartment> getAllApartments();
    Apartment getByIdWithDependency(String id);
    Apartment getById(String id);

    void addApartment(Apartment apartment) throws IllegalArgumentException;
    void deleteApartment(String id);
    void updateApartment(Apartment apartment);

    /*---------------calendar */
    boolean refreshDataBaseDate(List<Apartment> apartments);

    String setBufferUrlBooking(String idApartment);
    void downloadMyCalendar();

    int getCountOrderDay(String dateIn, String dateOut);
    User getCurrentUser() throws NotFoundException;
    User getUserByUserName(String userName);

    /*--------------------order*/
    Orders createOrder(String email, String nameUser, String dateOrder,
                     Date dateIn, Date dateOut, String apartmentId,
                     int status, String summary);

    List<Orders> getAllOrdersActive(int status);
    List<Orders> getAllOrders();
    List<Orders> getOrderByUsername(String username);
    int deleteOrder(String order, String userRole); // возращает размер пени за отмену
    Orders getOrderById(String id);
    //-----------------------picture
    void saveImageForApartment(MultipartFile file, String idApartment, String pathNewImage) throws IOException;
    byte[] getImageForApartment(String pathImage) throws IOException;
    //__________________________________________________
    void setCompleteOrder(Orders order, String apartmentId, Period period, String username);
    void console();
    void confirmOrderPaymentStatus(String idOrder, int status);
    /////----------------------
    boolean checkFreeApartmentForDate(String apartmentId, Date in, Date out);

    //////////
    // установка цен по периодам
    //
    // ////////
    List<CustomPricesModel> setPriceForThePeriod(Apartment apartment, String in, String out) throws ParseException;
    void addNewPrice(String name, String begin, String end, String price, String idApartment) throws ParseException;

    boolean hasThePriceChanged(List<CustomPricesModel> pricesModelList);
    //------------------
    String getNameDistrict(int id);

    ///////////////////
    Picture getPicture(int id);
    boolean deletePicture(int id);
}
