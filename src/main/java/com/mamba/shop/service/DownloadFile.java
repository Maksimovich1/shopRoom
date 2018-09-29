package com.mamba.shop.service;

import com.mamba.shop.entity.Period;

import java.io.IOException;
import java.util.List;

public interface DownloadFile {
    void saveFile(String filename, String url) throws IOException;
    List<Period> listenCalendarICS(String fileName);
}
