package com.mamba.shop.service;

import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Orders;
import com.mamba.shop.entity.Period;
import com.mamba.shop.entity.User;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

public interface ShopService {

    List<Apartment> searchFreeApartmentsWithDependency(
            String countPeople, String countChild,
            String district, String priceMax,
            String dateIn, String dateOut, String bedroom
    );
    List<Apartment> getAllApartments();
    Apartment getByIdWithDependency(String id);
    Apartment getById(String id);

    void addApartment(Apartment apartment);
    void deleteApartment(String id);
    void updateApartment(Apartment apartment);

    /*---------------calendar */
    boolean refreshDataBaseDate(List<Apartment> apartments);

    void setBufferUrlBooking(String idApartment);
    void downloadMyCalendar();

    int getCountOrderDay(String dateIn, String dateOut);
    User getCurrentUser() throws NotFoundException;

    /*--------------------order*/
    Orders createOrder(String email, String nameUser, String dateOrder,
                     Date dateIn, Date dateOut, String apartmentId,
                     int status, String summary);

    List<Orders> getAllOrdersActive(int status);
    List<Orders> getAllOrders();
    List<Orders> getOrderByUsername(String username);
    void deleteOrder(String order);
    //-----------------------picture
    void saveImageForApartment(CommonsMultipartFile[] file, String idApartment);
    //__________________________________________________
    void setCompleteOrder(Orders order, String apartmentId, Period period, String username);
    void console();
    void confirmOrderPaymentStatus(String idOrder, int status);
}
