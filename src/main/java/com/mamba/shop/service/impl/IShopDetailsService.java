package com.mamba.shop.service.impl;

import com.mamba.shop.dao.OrderDao;
import com.mamba.shop.dao.ProductDao;
import com.mamba.shop.entity.Orders;
import com.mamba.shop.entity.Product;
import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class IShopDetailsService implements ShopService{

    private final ProductDao productDao;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final OrderDao orderDao;

    @Autowired
    public IShopDetailsService(ProductDao productDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    @Override
    public List<Product> searchFreeRooms(String countPeople, String countChild, String priceMin, String priceMax, String dateIn, String dateOut) {

        int people = Integer.parseInt(countPeople);
        int child = Integer.parseInt(countChild);
        Double priceM = Double.parseDouble(priceMin);
        Double priceMx = Double.parseDouble(priceMax);
        Date date_in = null;
        Date date_out = null;
        try {
            date_in = new SimpleDateFormat(DATE_FORMAT).parse(dateIn);
            date_out = new SimpleDateFormat(DATE_FORMAT).parse(dateOut);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Product> searchListParamProduct = productDao.getProductForParam(people, child, priceM, priceMx);

        List<Orders> searchActiveOrders = orderDao.getActiveOrders(new Date());
        System.out.println("## номера");
        for (Product product: searchListParamProduct)
            System.out.println(product);

        System.out.println("## активные ордера");
        for (Orders orders: searchActiveOrders)
            System.out.println(orders);

        return selectFreeRooms(searchListParamProduct, searchActiveOrders, date_in, date_out);
    }


    private List<Product> selectFreeRooms(List<Product> searchListParamProduct, List<Orders> searchActiveOrders, Date date_in, Date date_out) {

        List<Product> finishResult = new ArrayList<>();

        for (Product product: searchListParamProduct){
            int idProduct = product.getId();
            boolean flag = false;
            List<Boolean> flags = new ArrayList<>();
            for (Orders order: searchActiveOrders){
                if (idProduct == order.getId_product_buy()){
                    //logic
                    Date order_date_in = null;
                    Date order_date_out = null;
                    try {
                        order_date_in = new SimpleDateFormat(DATE_FORMAT).parse(order.getDate_in());
                        order_date_out = new SimpleDateFormat(DATE_FORMAT).parse(order.getDate_out());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (order_date_in == null || order_date_out == null) throw new NullPointerException();


                    flag = date_in.before(order_date_in) && date_out.before(order_date_in) || date_in.after(order_date_out) && date_out.after(order_date_out);
                }
                flags.add(flag);
            }
            int n = flags.size();
            int k = 0;
            for (Boolean b: flags){
                if (b){
                    k++;
                }
            }
            if (k == n) finishResult.add(product);

        }
//        System.out.println("Результаты........");
//        if (finishResult.isEmpty()){
//            System.out.println("нет номеров по критериям");
//        }
//        else
//        for (Product product: finishResult)
//            System.out.println(product);

        return finishResult;
    }

}
