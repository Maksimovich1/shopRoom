package com.mamba.shop.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = -1000119078147252957L;

    private int id;
    private String name;
    private int countRoom;
    private int countPeople;
    private int countChild;
    private double price;
    private byte[] picture;

    public Product() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "count_room", nullable = false)
    public int getCountRoom() {
        return countRoom;
    }

    public void setCountRoom(int countRoom) {
        this.countRoom = countRoom;
    }

    @Column(name = "count_people", nullable = false)
    public int getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    @Column(name = "count_child", nullable = false)
    public int getCountChild() {
        return countChild;
    }

    public void setCountChild(int countChild) {
        this.countChild = countChild;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countRoom=" + countRoom +
                ", countPeople=" + countPeople +
                ", countChild=" + countChild +
                ", price=" + price +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }
}

