package com.mamba.shop.entity.custom_entity;

import java.util.Date;

public class SearchCustomModel {

    private int bedroom;
    private int people;
    private int child;
    private int price;
    private int district;
    private Date search_date_in;
    private Date search_date_out;

    public SearchCustomModel(int bedroom, int people, int child, int price,
                             int district, Date search_date_in,
                             Date search_date_out) {
        this.bedroom = bedroom;
        this.people = people;
        this.child = child;
        this.price = price;
        this.district = district;
        this.search_date_in = search_date_in;
        this.search_date_out = search_date_out;
    }

    public int getBedroom() {
        return bedroom;
    }

    public int getPeople() {
        return people;
    }

    public int getChild() {
        return child;
    }

    public int getPrice() {
        return price;
    }

    public int getDistrict() {
        return district;
    }

    public Date getSearch_date_in() {
        return search_date_in;
    }

    public Date getSearch_date_out() {
        return search_date_out;
    }
}
