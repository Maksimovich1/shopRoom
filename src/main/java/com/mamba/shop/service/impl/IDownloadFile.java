package com.mamba.shop.service.impl;

import com.mamba.shop.config.AppConfiguration;
import com.mamba.shop.entity.Period;
import com.mamba.shop.service.DownloadFile;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Summary;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IDownloadFile implements DownloadFile {

    private static String url = "https://admin.booking.com/hotel/hoteladmin/ical.html?t=" +
            "7OZDWQnPKjX6iJCd_r3gcp40qJ15MOn7pao19szymZV5db6L6AHQgnTipxDUtIM_-ytX307" +
            "NGYkm7vVlwFpR-Cb81HBBYIwciBwV2oID6Aud0oatr1qm3LGxV9PZVvJ4udpFwR2i" +
            "tykauNGrCUF7FL4aiBYaCOi7nG7jRQ";
    private static String fileName = "\\room1.ics";
    private static String dirname;

    public IDownloadFile() {
    }

    public IDownloadFile (String dirnameC) {
        dirname = dirnameC;
    }
        public static void main(String[] args) throws IOException {

        IDownloadFile downloadFile = new IDownloadFile("D:\\calendar");

        System.out.println("ok!");
        downloadFile.writeCalendar(fileName, new Period());
    }

    @Override
    public void saveFile(String filename, String url) throws IOException {
//        BufferedInputStream inputStream = null;
//        FileOutputStream outputStream = null;
//        try {
//            inputStream = new BufferedInputStream(new URL(url).openStream());
//            outputStream = new FileOutputStream(filename);
//            byte []data = new byte[1024];
//            int count;
//            while ((count = inputStream.read(data, 0, 1024)) != -1){
//                outputStream.write(data, 0, count);
//            }
//        }
//        finally {
//            if (inputStream != null)
//                inputStream.close();
//            if (outputStream != null)
//                outputStream.close();
//        }
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
    public void writeCalendar(String fileName, Period period) {
        FileInputStream fileInputStream;
        CalendarBuilder calendarBuilder = new CalendarBuilder();
        Calendar calendar;
        try {
            fileInputStream = new FileInputStream(dirname + fileName);
            calendar = calendarBuilder.build(fileInputStream);
            Date dateIn = new DateTime(period.getDate_in());
            Date dateOut = new DateTime(period.getDate_out());
            VEvent event = new VEvent(dateIn, dateOut, "");
            calendar.getComponents().add(event);
            FileOutputStream outputStream = new FileOutputStream(dirname + fileName);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, outputStream);

            System.out.println("123___");
        } catch (ParserException | IOException | ValidationException e) {
            e.printStackTrace();
        }
    }

}
