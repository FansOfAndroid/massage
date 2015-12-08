package com.dfsebook.mssage.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 15-10-24.
 */
public class Limit implements Serializable{

    private int count;

    private String date;

    private int real;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
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
