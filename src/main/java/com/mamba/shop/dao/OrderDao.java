package com.mamba.shop.dao;

import com.mamba.shop.entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    int addOrder(Orders order);
    void deleteOrder(Orders order);
    void updateOrder(Orders order);

    List<Orders> getAllOrders();
    List<Orders> getAllOrdersForDate(Date date);
    List<Orders> getActiveOrders(Date now);

    List<Orders> getOrderByUsername(String username);

}
