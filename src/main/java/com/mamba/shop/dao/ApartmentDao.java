package com.mamba.shop.dao;

import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.custom_entity.SearchCustomModel;

import java.util.List;

public interface ApartmentDao {

    List<Apartment> getAllApartmentListWithDependency();
    List<Apartment> getAllFreeApartmentsBySearchCustomModelWithDependency(SearchCustomModel customModel);
    Apartment findByIdWithDependency(String id);
    Apartment findById(String id);

    void setDisableApartment(boolean status);
    void updateApartment(Apartment apartment);

    void addApartment(Apartment apartment);
}
