package com.mamba.shop.service;

import com.mamba.shop.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> getOrdersByParameter(String idOrder, String status,
                                      String idRoom, String username);
}
