package com.mamba.shop.service;

import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Product;

import java.util.Date;
import java.util.List;

public interface ShopService {

    List<Product> searchFreeRooms(String countPeople, String countChild, String priceMin,
                        String priceMax, String dateIn, String dateOut);
    List<Apartment> searchFreeApartments(
            String countPeople, String countChild, String priceMin,
            String priceMax, Date dateIn, Date dateOut, String bedroom
    );
}
