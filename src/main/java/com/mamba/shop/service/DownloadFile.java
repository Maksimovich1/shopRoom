package com.mamba.shop.service;

import java.io.IOException;

public interface DownloadFile {
    void saveFile(String filename, String url) throws IOException;
    void listenCalendarICS(String pahtToFile);
}
