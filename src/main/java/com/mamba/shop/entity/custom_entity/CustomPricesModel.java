package com.mamba.shop.entity.custom_entity;

import java.io.Serializable;

public class CustomPricesModel implements Serializable {
    private String date;
    private String price;
    private boolean flag;

    public CustomPricesModel(String date, String price, boolean flag) {
        this.date = date;
        this.price = price;
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CustomPricesModel{" +
                "date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", flag=" + flag +
                '}';
    }
}

