package com.dfsebook.mssage.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 15-11-9.
 */
public class DialogProperty implements Serializable{

    private int requestype;

    private String positiveButtonText;

    private String negativeButtonText;

    private String hintText;

    public DialogProperty() {
    }

    public DialogProperty(int requestype, String positiveButtonText, String negativeButtonText, String hintText) {
        this.requestype = requestype;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText = negativeButtonText;
        this.hintText = hintText;
    }

    public int getRequestype() {
        return requestype;
    }

    public void setRequestype(int requestype) {
        this.requestype = requestype;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }
}
