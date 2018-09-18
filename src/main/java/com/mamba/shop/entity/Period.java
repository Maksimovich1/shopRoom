package com.mamba.shop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "period")
public class Period implements Serializable{

    private int id;
    private Date date_in;
    private Date date_out;
    private Set<Apartment> apartments =
            new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_in")
    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_out")
    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    @ManyToMany
    @JoinTable(name = "apartment_period",
        joinColumns = @JoinColumn(name = "period_id"),
        inverseJoinColumns = @JoinColumn(name = "apartment_id"))
    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", date_in=" + date_in.toString() +
                ", date_out=" + date_out.toString() +
                '}';
    }
}
