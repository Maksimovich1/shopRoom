package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.PeriodDao;
import com.mamba.shop.entity.Period;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class IPeriodDao implements PeriodDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IPeriodDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPeriod(Period period) {

    }

    @Override
    public void deletePeriod(Period period) {
        System.out.println("###deleteOpderbyId id = " + period.getId());
        sessionFactory.getCurrentSession().delete(period);
    }

    @Override
    public List<Period> getAllPeriods() {
        return null;
    }

    @Override
    public Period getById(int id) {
        System.out.println("###getByIdPeriod_____________");
        return sessionFactory.getCurrentSession().get(Period.class, id);
    }
}
