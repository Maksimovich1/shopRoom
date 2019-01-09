package com.mamba.shop;

import com.mamba.shop.config.AppConfiguration;
import com.mamba.shop.dao.ApartmentDao;
import com.mamba.shop.entity.*;
import com.mamba.shop.service.MailService;
import com.mamba.shop.service.ShopService;
import com.mamba.shop.service.impl.IShopDetailsService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        /*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("5250425_Os"));*/

        System.out.println(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT)
                .format(new Date(1546376400000L)));

    }
}
