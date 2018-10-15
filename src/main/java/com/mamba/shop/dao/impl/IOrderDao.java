package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.OrderDao;
import com.mamba.shop.entity.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class IOrderDao implements OrderDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IOrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addOrder(Orders order) {
        sessionFactory.getCurrentSession().persist(order);
        return 0;
    }

    @Override
    public void deleteOrder(Orders order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    public void updateOrder(Orders order) {

    }

    @Transactional(readOnly = true)
    @Override
    public List<Orders> getAllOrders() {
        return null;
    }

    @Override
    public List<Orders> getActiveOrders(Date now) {
        return null;
    }

    @Override
    public List<Orders> getOrderByUsername(String username) {
        return null;
    }
}
