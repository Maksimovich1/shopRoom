package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.OrderDao;
import com.mamba.shop.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class OrderDaoImpl implements OrderDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
        System.out.println("AddOrder!!");
        session.close();
    }

    @Override
    public void deleteOrder(Orders order) {

    }

    @Override
    public void updateOrder(Orders order) {

    }

    @Override
    public List<Orders> getAllOrders() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Orders> getActiveOrders(Date now) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders where data_out >:now");
        query.setParameter("now", now);
        List<Orders> ordersList = query.list();
        transaction.commit();
        session.close();

        System.out.println("## Получение активных заказов. Сейчас " + now.toString());
        return ordersList;
    }
}
