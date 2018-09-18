package com.mamba.shop;

import com.mamba.shop.config.AppConfiguration;
import com.mamba.shop.dao.ApartmentDao;
import com.mamba.shop.entity.*;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Summary;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        ApartmentDao dao = context.getBean(ApartmentDao.class);
        List<Apartment> list = dao.getAllApartmentListWithDependency();
        for (Apartment ap :
                list) {
            for (Period period: ap.getPeriods()){
                System.out.println(period);
            }
        }

        //        ProductDao dao = context.getBean(ProductDao.class);
//
//        ShopService shopService = context.getBean(ShopService.class);
//
//        shopService.searchFreeRooms("2","1","40", "70","2018-08-25", "2018-09-11");



//        OrderDao orderDao = context.getBean(OrderDao.class);
//        List<Orders> ordersList = orderDao.getActiveOrders(new Date());
//        for (Orders orders: ordersList)
//            System.out.println(orders);



//        List<Product> allProduct = dao.getProductForParam(2,1,50,80);
//
//        for (Product p :
//                allProduct) {
//            System.out.println(p.toString());
//        }


//        Orders order = new Orders();
//        order.setDate_order("2018-08-25");
//        order.setCustomer_address("qwerty");
//        order.setCustomer_email("qwqeqw@sw");
//        order.setCustomer_name("andrey");
//        order.setCustomer_phone("342453356");
//        order.setDate_in("2018-08-25");
//        order.setDate_out("2018-08-25");
//        order.setId_product_buy(1);
//        order.setPrice("60");
//        order.setName_product(allProduct.get(1).getName());


        //context.getBean(OrderDao.class).addOrder(order);
//        try {
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(order.getDate_order());
//            System.out.println(date.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }





//        try {
//            //listenCalendarICS(new FileInputStream("C:/Users/Andrew/Desktop/room3108816011.ics"));
//            URL url = new URL("https://admin.booking.com/hotel/hoteladmin/ical.html?t=7OZDWQnPKjX6iJCd_r3gcp40qJ15MOn7pao19szymZV5db6L6AHQgnTipxDUtIM_-ytX307NGYkm7vVlwFpR-Cb81HBBYIwciBwV2oID6Aud0oatr1qm3LGxV9PZVvJ4udpFwR2itykauNGrCUF7FL4aiBYaCOi7nG7jRQ");
//            BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
//            //listenCalendarICS(new FileInputStream(new File(inputStream.toString())));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public File getFileForURL(String urlSrt) throws IOException {
        URL url = new URL(urlSrt);
        BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
        FileInputStream fileInputStream = new FileInputStream(url.getFile());
        return null;
    }

    public static void listenCalendarICS(FileInputStream fileInputStream){

        CalendarBuilder calendarBuilder = new CalendarBuilder();
        try {
            Calendar calendar = calendarBuilder.build(fileInputStream);
            ComponentList list = calendar.getComponents(Component.VEVENT);
            for (Object obj: list){
                VEvent event = (VEvent) obj;
                DtStart propIn = (DtStart) event.getProperties().get(0);
                DtEnd propOut = (DtEnd) event.getProperties().get(1);
                Summary inform = (Summary) event.getProperties().get(3);
                String in = propIn.getDate().toString();
                String out = propOut.getDate().toString();
                String about = inform.getValue();
                //String description = (String) o;
//                String title = event.getSummary().getValue();
                System.out.println("Въезд: " + in + ", выезд: " + out + ".\nInformation: " + about);
                System.out.println("-----------------------");
            }
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }

    }
}
