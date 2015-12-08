package com.dfsebook.mssage.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 15-10-17.
 */
public class Appointment implements Serializable{

    private String year;

    private String month;

    private String day;

    private String chineseWeekDay;

    private boolean showHeader;

    private boolean weekend;

    private int limitNum;

    private int ocupied;

    private int requestNum;//

    private boolean there;//remark your appointment where show

    private List<AppointmentInfo> appointmentInfos;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getChineseWeekDay() {
        return chineseWeekDay;
    }

    public void setChineseWeekDay(String chineseWeekDay) {
        this.chineseWeekDay = chineseWeekDay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getOcupied() {
        return ocupied;
    }

    public void setOcupied(int ocupied) {
        this.ocupied = ocupied;
    }

    public int getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }

    public boolean isThere() {
        return there;
    }

    public void setThere(boolean there) {
        this.there = there;
    }

    public List<AppointmentInfo> getAppointmentInfos() {
        return appointmentInfos;
    }

    public void setAppointmentInfos(List<AppointmentInfo> appointmentInfos) {
        this.appointmentInfos = appointmentInfos;
    }

    @Override
    public String toString() {
        return year  + "-" + month.substring(0,month.length()-1) + "-" + day ;
    }
}
