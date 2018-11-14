package com.mamba.shop.service.impl;

import com.mamba.shop.dao.OrderDao;
import com.mamba.shop.entity.Orders;
import com.mamba.shop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class IOrderService implements OrdersService {

    private final OrderDao orderDao;

    @Autowired
    public IOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /*
    * Получение ордеров по переданным параметрам
    * состовляет SQL запрос по поступившим параметрам
    * Если параметр пуст то он не попадает в запрос
    * */
    @Transactional(readOnly = true)
    @Override
    public List<Orders> getOrdersByParameter(String idOrder, String status,
                                             String idRoom, String username) {
     final String beginSQL = "from Orders where status_order = :status";
     String bodySQL = "";
     /*Date date = null;*/
     if (!idOrder.equals("")){
         bodySQL += " and id = :idOrder";
     }
     if (!idRoom.equals("")){
         bodySQL += " and apartment_id = :idRoom";
     }
     /*if (!dateOrder.equals("")){

         try {
             date = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateOrder);
              bodySQL += " and data_order = :dateOrder";
         } catch (ParseException e) {
             System.out.println("Дата не в формате гггг-мм-дд!");
             e.printStackTrace();
             date = null;
         }
     }*/
     if (!username.equals("")){
         bodySQL += " and customer_name = :username";
     }
     final String finishSQL = beginSQL + bodySQL;
        System.out.println(finishSQL);

        return orderDao.getAllOrdersForParam(finishSQL, idOrder, status, idRoom, username);
    }
}
