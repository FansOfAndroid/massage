package com.dfsebook.mssage.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 15-10-9.
 */
public class ActivePicture implements Serializable{

    private String imageUrl;

    private boolean showDetail;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isShowDetail() {
        return showDetail;
    }

    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
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
