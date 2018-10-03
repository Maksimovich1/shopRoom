package com.mamba.shop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_rooms")
public class Orders implements Serializable {
    private int id_order;
    private String date_order;
    private String customer_email;
    private String customer_name;
    private String customer_phone;
    private Date date_in;
    private Date date_out;
    private String id_product_buy;
    private String price;
    private int status;

    public Orders() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    @Column(name = "data_order", nullable = false)
    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    @Column(name = "customer_email", nullable = false)
    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    @Column(name = "customer_name", nullable = false)
    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    @Column(name = "customer_phone", nullable = false)
    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_in", nullable = false)
    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_out", nullable = false)
    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    @Column(name = "apartment_id", nullable = false)
    public String getId_product_buy() {
        return id_product_buy;
    }

    public void setId_product_buy(String id_product_buy) {
        this.id_product_buy = id_product_buy;
    }

    @Column(name = "summary", nullable = false)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Column(name = "status_order", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id_order=" + id_order +
                ", date_order=" + date_order +
                ", customer_email='" + customer_email + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", date_in=" + date_in +
                ", date_out=" + date_out +
                ", id_product_buy=" + id_product_buy +
                ", price='" + price + '\'' +
                '}';
    }
}
