package com.mamba.shop.dao;

import com.mamba.shop.entity.PriceForThePeriod;

import java.util.List;

public interface PriceForPeriodDao {

    List<PriceForThePeriod> getPriceForThePeriodByIdApartment(String idApartment);
    void addPriceForPeriod(PriceForThePeriod priceForThePeriod);
    void deletePriceForPeriod(int id);

}
