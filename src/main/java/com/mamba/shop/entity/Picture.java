package com.mamba.shop.entity;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "picture")
public class Picture {

    private int id;
    private Apartment apartment;
    private byte[] pict;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Column(name = "pic")
    public byte[] getPict() {
        return pict;
    }

    public void setPict(byte[] pict) {
        this.pict = pict;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", apartment=" + apartment.getId() +
                ", pict=" + pict +
                '}';
    }
}
