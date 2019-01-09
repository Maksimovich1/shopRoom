package com.mamba.shop.entity;

import javax.persistence.*;

@Entity
@Table(name = "period_price")
@NamedQueries({
        @NamedQuery(
                name = "PriceForThePeriod.getAllPriceForThePeriod",
                query = "from PriceForThePeriod where id_apartment = :id"
        )
})
public class PriceForThePeriod {

    private int id;
    private String name_period;
    private long begin_period;
    private long end_period;
    private String priceOfPeriod;
    private String id_apartment;

    public PriceForThePeriod() {
    }

    public PriceForThePeriod(String name_period, long begin_period, long end_period, String priceOfPeriod, String id_apartment) {
        this.name_period = name_period;
        this.begin_period = begin_period;
        this.end_period = end_period;
        this.priceOfPeriod = priceOfPeriod;
        this.id_apartment = id_apartment;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name_period")
    public String getName_period() {
        return name_period;
    }

    public void setName_period(String name_period) {
        this.name_period = name_period;
    }

    @Column(name = "begin_period")
    public long getBegin_period() {
        return begin_period;
    }

    public void setBegin_period(long begin_period) {
        this.begin_period = begin_period;
    }

    @Column(name = "end_period")
    public long getEnd_period() {
        return end_period;
    }

    public void setEnd_period(long end_period) {
        this.end_period = end_period;
    }

    @Column(name = "priceofperiod")
    public String getPriceOfPeriod() {
        return priceOfPeriod;
    }

    public void setPriceOfPeriod(String priceOfPeriod) {
        this.priceOfPeriod = priceOfPeriod;
    }

    @Column(name = "id_apartment")
    public String getId_apartment() {
        return id_apartment;
    }

    public void setId_apartment(String id_apartment) {
        this.id_apartment = id_apartment;
    }

    @Override
    public String toString() {
        return "PriceForThePeriod{" +
                "id=" + id +
                ", name_period='" + name_period + '\'' +
                ", begin_period=" + begin_period +
                ", end_period=" + end_period +
                ", priceOfPeriod='" + priceOfPeriod + '\'' +
                ", id_apartment='" + id_apartment + '\'' +
                '}';
    }
}
