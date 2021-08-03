package com.example.caronas.models;

public class Ride {

    private Long id;
    private Long user_id;
    private String phone;
    private String from_city;
    private String from_neighborhood;
    private String from_street;
    private String to_city;
    private String to_neighborhood;
    private String to_street;
    private String start_date;
    private String end_date;
    private Long canceled;

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getFrom_neighborhood() {
        return from_neighborhood;
    }

    public void setFrom_neighborhood(String from_neighborhood) {
        this.from_neighborhood = from_neighborhood;
    }

    public String getFrom_street() {
        return from_street;
    }

    public void setFrom_street(String from_street) {
        this.from_street = from_street;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public String getTo_neighborhood() {
        return to_neighborhood;
    }

    public void setTo_neighborhood(String to_neighborhood) {
        this.to_neighborhood = to_neighborhood;
    }

    public String getTo_street() {
        return to_street;
    }

    public void setTo_street(String to_street) {
        this.to_street = to_street;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Long isCanceled() {
        return canceled;
    }

    public void setCanceled(Long canceled) {
        this.canceled = canceled;
    }

}
