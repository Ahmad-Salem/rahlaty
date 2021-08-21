package com.example.ahmedsalemalbiady.rahlaty.Model;

public class Trip_model {
    private String id;
    private String trvale_date;
    private String destination_city;
    private String duration_days;
    private String residence;
    private String bus_info;
    private String number_of_subscriber;
    private String trip_program;
    private String bus_pas_complete;
    private String trop_photo;
    private String price;
    private String photo_1_value,photo_2_value,photo_3_value,photo_4_value,photo_5_value,photo_6_value,photo_title_1_value,photo_title_2_value,photo_title_3_value,photo_title_4_value,photo_title_5_value,photo_title_6_value;
    private String trip_status;
    //defualt contractor
    public Trip_model() {
    }

    //custom constractor

    public Trip_model(String trip_status,String id, String trvale_date, String destination_city, String duration_days, String residence, String bus_info, String number_of_subscriber, String trip_program, String bus_pas_complete, String trop_photo, String price, String photo_1_value, String photo_2_value, String photo_3_value, String photo_4_value, String photo_5_value, String photo_6_value, String photo_title_1_value, String photo_title_2_value, String photo_title_3_value, String photo_title_4_value, String photo_title_5_value, String photo_title_6_value) {
        this.id = id;
        this.trip_status = trip_status;
        this.trvale_date = trvale_date;
        this.destination_city = destination_city;
        this.duration_days = duration_days;
        this.residence = residence;
        this.bus_info = bus_info;
        this.number_of_subscriber = number_of_subscriber;
        this.trip_program = trip_program;
        this.bus_pas_complete = bus_pas_complete;
        this.trop_photo = trop_photo;
        this.price = price;
        this.photo_1_value = photo_1_value;
        this.photo_2_value = photo_2_value;
        this.photo_3_value = photo_3_value;
        this.photo_4_value = photo_4_value;
        this.photo_5_value = photo_5_value;
        this.photo_6_value = photo_6_value;
        this.photo_title_1_value = photo_title_1_value;
        this.photo_title_2_value = photo_title_2_value;
        this.photo_title_3_value = photo_title_3_value;
        this.photo_title_4_value = photo_title_4_value;
        this.photo_title_5_value = photo_title_5_value;
        this.photo_title_6_value = photo_title_6_value;
    }


    //setter

    public void setTrip_status(String trip_status) {
        this.trip_status = trip_status;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setTrvale_date(String trvale_date) {
        this.trvale_date = trvale_date;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public void setDuration_days(String duration_days) {
        this.duration_days = duration_days;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setBus_info(String bus_info) {
        this.bus_info = bus_info;
    }

    public void setNumber_of_subscriber(String number_of_subscriber) {
        this.number_of_subscriber = number_of_subscriber;
    }

    public void setTrip_program(String trip_program) {
        this.trip_program = trip_program;
    }

    public void setBus_pas_complete(String bus_pas_complete) {
        this.bus_pas_complete = bus_pas_complete;
    }

    public void setTrop_photo(String trop_photo) {
        this.trop_photo = trop_photo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhoto_1_value(String photo_1_value) {
        this.photo_1_value = photo_1_value;
    }

    public void setPhoto_2_value(String photo_2_value) {
        this.photo_2_value = photo_2_value;
    }

    public void setPhoto_3_value(String photo_3_value) {
        this.photo_3_value = photo_3_value;
    }

    public void setPhoto_4_value(String photo_4_value) {
        this.photo_4_value = photo_4_value;
    }

    public void setPhoto_5_value(String photo_5_value) {
        this.photo_5_value = photo_5_value;
    }

    public void setPhoto_6_value(String photo_6_value) {
        this.photo_6_value = photo_6_value;
    }

    public void setPhoto_title_1_value(String photo_title_1_value) {
        this.photo_title_1_value = photo_title_1_value;
    }

    public void setPhoto_title_2_value(String photo_title_2_value) {
        this.photo_title_2_value = photo_title_2_value;
    }

    public void setPhoto_title_3_value(String photo_title_3_value) {
        this.photo_title_3_value = photo_title_3_value;
    }

    public void setPhoto_title_4_value(String photo_title_4_value) {
        this.photo_title_4_value = photo_title_4_value;
    }

    public void setPhoto_title_5_value(String photo_title_5_value) {
        this.photo_title_5_value = photo_title_5_value;
    }

    public void setPhoto_title_6_value(String photo_title_6_value) {
        this.photo_title_6_value = photo_title_6_value;
    }


    //getter


    public String getId() {
        return id;
    }

    public String getTrip_status() {
        return trip_status;
    }

    public String getTrvale_date() {
        return trvale_date;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public String getDuration_days() {
        return duration_days;
    }

    public String getResidence() {
        return residence;
    }

    public String getBus_info() {
        return bus_info;
    }

    public String getNumber_of_subscriber() {
        return number_of_subscriber;
    }

    public String getTrip_program() {
        return trip_program;
    }

    public String getBus_pas_complete() {
        return bus_pas_complete;
    }

    public String getTrop_photo() {
        return trop_photo;
    }

    public String getPrice() {
        return price;
    }

    public String getPhoto_1_value() {
        return photo_1_value;
    }

    public String getPhoto_2_value() {
        return photo_2_value;
    }

    public String getPhoto_3_value() {
        return photo_3_value;
    }

    public String getPhoto_4_value() {
        return photo_4_value;
    }

    public String getPhoto_5_value() {
        return photo_5_value;
    }

    public String getPhoto_6_value() {
        return photo_6_value;
    }

    public String getPhoto_title_1_value() {
        return photo_title_1_value;
    }

    public String getPhoto_title_2_value() {
        return photo_title_2_value;
    }

    public String getPhoto_title_3_value() {
        return photo_title_3_value;
    }

    public String getPhoto_title_4_value() {
        return photo_title_4_value;
    }

    public String getPhoto_title_5_value() {
        return photo_title_5_value;
    }

    public String getPhoto_title_6_value() {
        return photo_title_6_value;
    }
}
