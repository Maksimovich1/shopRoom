package com.mamba.shop.dao;

import com.mamba.shop.entity.Period;

import java.util.List;

public interface PeriodDao {

    void addPeriod(Period period);

    void deletePeriod(Period period);
    List<Period> getAllPeriods();
    Period getById(int id);

}
