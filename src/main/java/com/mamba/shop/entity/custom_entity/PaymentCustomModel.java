package com.mamba.shop.entity.custom_entity;


public class PaymentCustomModel {
    private String apartmentName;
    private String dateIn;
    private String dateOut;
    private int countNight;
    private String summary;
    private String districtName;
    private String username;
    private String latitude;
    private String longitude;
    private int pledge;

    public PaymentCustomModel(String apartmentName, String dateIn,
                              String dateOut, int countNight, int pledge,
                              String summary, String districtName, String username) {
        this.apartmentName = apartmentName;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.countNight = countNight;
        this.summary = summary;
        this.districtName = districtName;
        this.username = username;
        this.pledge = pledge;
        this.latitude = "";
        this.longitude = "";
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public int getCountNight() {
        return countNight;
    }

    public String getSummary() {
        return summary;
    }

    public String getUsername() {
        return username;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

        public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getPledge() {
        return pledge;
    }

    public void setPledge(int pledge) {
        this.pledge = pledge;
    }
}
