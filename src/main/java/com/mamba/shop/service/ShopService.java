package com.mamba.shop.service;

import com.mamba.shop.entity.Product;

import java.util.List;

public interface ShopService {

    List<Product> searchFreeRooms(String countPeople, String countChild, String priceMin,
                        String priceMax, String dateIn, String dateOut);
}
