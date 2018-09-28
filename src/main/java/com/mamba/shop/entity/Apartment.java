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

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToMany(fetch = FetchType.LAZY)
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
