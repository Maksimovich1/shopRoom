package com.mamba.shop.entity.custom_entity;

public class PeriodLongCustomModel {

    private long begin;
    private long end;
    private String price;

    public PeriodLongCustomModel(long begin, long end, String price) {
        this.begin = begin;
        this.end = end;
        this.price = price;
    }

    public long getBegin() {
        return begin;
    }

    public long getEnd() {
        return end;
    }

    public String getPrice() {
        return price;
    }
}
