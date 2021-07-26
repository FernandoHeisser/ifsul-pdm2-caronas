package com.example.caronas.models;

import java.util.Date;

public class Request {

    private Long id;
    private String user_id;
    private String phone;
    private String from_city;
    private String from_neighborhood;
    private String from_street;
    private String to_city;
    private String to_neighborhood;
    private String to_street;
    private Date start_date;
    private Date end_date;
    private boolean canceled;

    public Long getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
