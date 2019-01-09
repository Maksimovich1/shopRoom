package com.mamba.shop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "apartment")
@NamedQueries({
        @NamedQuery(name = "Apartment.findAllWithDependency",
        query = "select distinct ap from Apartment " +
                "ap left join fetch ap.periods per " +
                "left join fetch ap.pictures pic"
        ),
        @NamedQuery(name = "Apartment.findByIdWithDependency",
        query = "select distinct ap from Apartment " +
                "ap left join fetch ap.periods per " +
                "left join fetch ap.pictures pic " +
                "where ap.id = :id"
        ),
        @NamedQuery(name = "Apartment.findFreeApartmentsBySearchCustomModelWithDependency",
        query = "select distinct ap from Apartment " +
                "ap left join fetch ap.periods per " +
                "left join fetch ap.pictures pic " +
                "where ap.bedroom = :p_bedroom and " +
                "ap.people = :p_people and " +
                "ap.children = :p_children and " +
                "ap.price <= :p_price and " +
                "ap.district = :p_district and " +
                "ap.enable = :p_enable")
})
public class Apartment implements Serializable {

    private String id;
    private int bedroom;
    private int people;
    private int children;
    private int price;
    private int district;
    private int enable;
    private String about;
    private String urlBooking;
    private String latitude;
    private String longitude;
    private int bathroom;
    private int size_room;
    private boolean wifi;
    private String address;
    private String nameApartment;
    private String nameManager;
    private String phoneManager;
    private String emailManager;
    private String check_in;
    private String check_out;
    private int possessions;
    private boolean animal;
    private boolean pool;
    private boolean heated_pool;
    private int bed1;
    private int bed2;
    private int name_partner;
    private int discount;
    private int percent_of_price;
    private Set<Picture> pictures =
            new HashSet<>();
    private Set<Period> periods =
            new HashSet<>();
    @Id
    @Column(name = "id_apartment")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "count_bedroom")
    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    @Column(name = "count_people")
    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    @Column(name = "count_child")
    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "district")
    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    @Column(name = "enable_apartment")
    public int isEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    @Column(name = "about_apartment")
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "urlBooking")
    public String getUrlBooking() {
        return urlBooking;
    }

    public void setUrlBooking(String urlBooking) {
        this.urlBooking = urlBooking;
    }

    @Column(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name = "count_bathroom")
    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    @Column(name = "size_room")
    public int getSize_room() {
        return size_room;
    }

    public void setSize_room(int size_room) {
        this.size_room = size_room;
    }

    @Column(name = "wifi")
    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "name_apartment")
    public String getNameApartment() {
        return nameApartment;
    }

    public void setNameApartment(String nameApartment) {
        this.nameApartment = nameApartment;
    }

    @Column(name = "name_manager")
    public String getNameManager() {
        return nameManager;
    }

    public void setNameManager(String nameManager) {
        this.nameManager = nameManager;
    }

    @Column(name = "phone_manager")
    public String getPhoneManager() {
        return phoneManager;
    }

    public void setPhoneManager(String phoneManager) {
        this.phoneManager = phoneManager;
    }

    @Column(name = "email_manager")
    public String getEmailManager() {
        return emailManager;
    }

    public void setEmailManager(String emailManager) {
        this.emailManager = emailManager;
    }

    @Column(name = "check_in")
    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    @Column(name = "check_out")
    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    @Column(name = "save_possessions")
    public int getPossessions() {
        return possessions;
    }

    public void setPossessions(int possessions) {
        this.possessions = possessions;
    }

    @Column(name = "animal")
    public boolean isAnimal() {
        return animal;
    }

    public void setAnimal(boolean animal) {
        this.animal = animal;
    }

    @Column(name = "pool")
    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    @Column(name = "heated_pool")
    public boolean isHeated_pool() {
        return heated_pool;
    }

    public void setHeated_pool(boolean heated_pool) {
        this.heated_pool = heated_pool;
    }

    @Column(name = "bed1")
    public int getBed1() {
        return bed1;
    }

    public void setBed1(int bed1) {
        this.bed1 = bed1;
    }

    @Column(name = "bed2")
    public int getBed2() {
        return bed2;
    }

    public void setBed2(int bed2) {
        this.bed2 = bed2;
    }

    @Column(name = "name_partner")
    public int getName_partner() {
        return name_partner;
    }

    public void setName_partner(int name_partner) {
        this.name_partner = name_partner;
    }

    @Column(name = "discount")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Column(name = "percent")
    public int getPercent_of_price() {
        return percent_of_price;
    }

    public void setPercent_of_price(int percent_of_price) {
        this.percent_of_price = percent_of_price;
    }

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "apartment_period",
        joinColumns = @JoinColumn(name = "apartment_id"),
        inverseJoinColumns = @JoinColumn(name = "period_id"))
    public Set<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(Set<Period> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", bedroom=" + bedroom +
                ", people=" + people +
                ", children=" + children +
                ", price=" + price +
                ", district=" + district +
                ", enable=" + enable +
                ", about='" + about + '\'' +
                ", pictures=" + pictures.size() +
                 ", order= " + getPeriods().size() +
                '}' + "\n";
    }
}
