package com.dfsebook.mssage.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 15-10-11.
 */
public class Sharing implements Serializable{

    private int sharingId ;

    private int customerId ;

    private String customerName ;

    private int sharingTitle ;

    private String sharingContent ;

    private String sharingDate ;

    private boolean newSharing;

    private boolean selfShow;

    private String customerPhoto;

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public boolean isSelfShow() {
        return selfShow;
    }

    public void setSelfShow(boolean selfShow) {
        this.selfShow = selfShow;
    }

    public int getSharingId() {
        return sharingId;
    }

    public void setSharingId(int sharingId) {
        this.sharingId = sharingId;
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

    public int getSharingTitle() {
        return sharingTitle;
    }

    public void setSharingTitle(int sharingTitle) {
        this.sharingTitle = sharingTitle;
    }

    public String getSharingContent() {
        return sharingContent;
    }

    public void setSharingContent(String sharingContent) {
        this.sharingContent = sharingContent;
    }

    public String getSharingDate() {
        return sharingDate;
    }

    public void setSharingDate(String sharingDate) {
        this.sharingDate = sharingDate;
    }

    public boolean isNewSharing() {
        return newSharing;
    }

    public void setNewSharing(boolean newSharing) {
        this.newSharing = newSharing;
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
