package com.mamba.shop.service.impl;

import com.mamba.shop.config.AppConfiguration;
import com.mamba.shop.entity.Period;
import com.mamba.shop.service.DownloadFile;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.UidGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;

public class IDownloadFile implements DownloadFile {

    private String dirname;

    public IDownloadFile() {
    }

    public IDownloadFile (String dirnameC) {
        dirname = dirnameC;
    }
        public static void main(String[] args) throws Exception {

        IDownloadFile downloadFile = new IDownloadFile("D:\\calendar");

        Period period = new Period();
        period.setDate_in(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse("2018-09-01"));
        period.setDate_out(new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse("2018-09-28"));
        System.out.println("initial...........!");
        downloadFile.writeCalendar("\\room2.ics", period, "testing date", "Europe/Minsk");
    }

    @Override
    public void saveFile(String filename, String url) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(dirname + filename));
    }

    @Override
    public List<Period> listenCalendarICS(String file) {
        String pathToFile = dirname + file;
        List<Period> periods = new ArrayList<>();
        CalendarBuilder calendarBuilder = new CalendarBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            Calendar calendar = calendarBuilder.build(fileInputStream);
            ComponentList list = calendar.getComponents(Component.VEVENT);
            for (Object obj: list){
                VEvent event = (VEvent) obj;
                DtStart propIn = (DtStart) event.getProperties().get(0);
                DtEnd propOut = (DtEnd) event.getProperties().get(1);
                Summary inform = (Summary) event.getProperties().get(3);
                // запись в лист всех дат полученных с букинга
                Period period = new Period();
                period.setDate_in(propIn.getDate());
                period.setDate_out(propOut.getDate());
                periods.add(period);
                // вывод в консоль
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
        return periods;
    }

    @Override
    public void writeCalendar(String fileName, Period period, String eventName, String timeZone) {
        FileInputStream fileInputStream;
        CalendarBuilder calendarBuilder = new CalendarBuilder();

        Calendar icsCalendar;
        try {
            if (new File(dirname + fileName).exists()) {
                fileInputStream = new FileInputStream(dirname + fileName);
                icsCalendar = calendarBuilder.build(fileInputStream);
            }else {
                icsCalendar = new Calendar();
                icsCalendar.getProperties().add(new ProdId("-//tenerife"));
                icsCalendar.getProperties().add(Version.VERSION_2_0);
                icsCalendar.getProperties().add(CalScale.GREGORIAN);
            }
//           // Устанавливаем часовой пояс
            TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
            TimeZone timezone = registry.getTimeZone(timeZone);
            VTimeZone tz = timezone.getVTimeZone();

            DateTime start = new DateTime(period.getDate_in());
            DateTime end = new DateTime(period.getDate_out());
            VEvent meeting = new VEvent(start, end, eventName);
// Добавляем к нему информацию о часовом поясе
            meeting.getProperties().add(tz.getTimeZoneId());
// генерируем uid
            UidGenerator generator = new UidGenerator("tenerifeperfect");
            meeting.getProperties().add(generator.generateUid());
// Добавляем к календарю созданное событие
            icsCalendar.getComponents().add(meeting);

            FileOutputStream outputStream = new FileOutputStream(dirname + fileName);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(icsCalendar, outputStream);
            outputStream.close();

            System.out.println("success");
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
            System.out.println("Ошибка создания календаря!");
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadCalendar(String id, HttpServletResponse response) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(dirname + "\\room234" + id + ".ics");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/calendar");

        response.setHeader("Content-Disposition","attachment; filename=\"" + "Room123e_wwlE_xxx" + id + ".ics" +"\"");
        try {
            assert file != null;
            FileCopyUtils.copy(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
