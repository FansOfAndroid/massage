package com.dfsebook.mssage.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 15-10-18.
 */
public class AppointmentInfo implements Serializable{

    private int customerId;

    private String customerName;

    private String customerPhoto;

    private String appointmentDate;

    private int appointmentStation;

    private boolean owner;

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentStation() {
        return appointmentStation;
    }

    public void setAppointmentStation(int appointmentStation) {
        this.appointmentStation = appointmentStation;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String gsonString = gson.toJson(this);
        try {
            gsonString = URLEncoder.encode(gsonString, "UTF-8");
            return gsonString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
