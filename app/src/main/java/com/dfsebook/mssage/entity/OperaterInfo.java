package com.dfsebook.mssage.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 15-11-6.
 */
public class OperaterInfo implements Serializable{

    private int customerId;

    private int questionId;

    private int replyId;

    private int sharingId;

    private String customerName;

    private String customerPhoto;

    private int infoType;

    private int limit;

    private int ocupy;

    private String content;

    private String dateString;

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

    public int getInfoType() {
        return infoType;
    }

    public void setInfoType(int infoType) {
        this.infoType = infoType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOcupy() {
        return ocupy;
    }

    public void setOcupy(int ocupy) {
        this.ocupy = ocupy;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getSharingId() {
        return sharingId;
    }

    public void setSharingId(int sharingId) {
        this.sharingId = sharingId;
    }
}
