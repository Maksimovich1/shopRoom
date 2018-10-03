package com.mamba.shop.service;

import com.mamba.shop.entity.Apartment;

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

    boolean refreshDataBaseDate(List<Apartment> apartments);

}
