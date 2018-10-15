package com.mamba.shop.service;

import com.mamba.shop.entity.Period;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DownloadFile {
    void saveFile(String filename, String url) throws IOException;
    List<Period> listenCalendarICS(String fileName);
    void writeCalendar(String fileName, Period period, String eventName, String timeZone);
    void downloadCalendar(String id, HttpServletResponse response);
}
