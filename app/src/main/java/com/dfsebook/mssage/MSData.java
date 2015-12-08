package com.dfsebook.mssage;

import android.app.Activity;

import com.dfsebook.mssage.entity.AppVersion;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.entity.Limit;
import com.dfsebook.mssage.entity.OperaterInfo;
import com.dfsebook.mssage.entity.Question;
import com.dfsebook.mssage.entity.Refresh;
import com.dfsebook.mssage.entity.Reply;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.Lunar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 15-11-3.
 */
public class MSData {

    private AppVersion appVersion;

    private List<Customer> customers;

    private List<Question> questions;

    private List<Reply> replies;

    private List<Limit> limits;

    private List<Sharing> sharings;

    private List<AppointmentInfo> appointmentInfos;

    private List<String> pictures;

    private List<String> touxiangs;

    private List<Refresh> refreshs;

    private List<OperaterInfo> operaterInfos;

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(List<Limit> limits) {
        this.limits = limits;
    }

    public List<Sharing> getSharings() {
        return sharings;
    }

    public void setSharings(List<Sharing> sharings) {
        this.sharings = sharings;
    }

    public List<AppointmentInfo> getAppointmentInfos() {
        return appointmentInfos;
    }

    public void setAppointmentInfos(List<AppointmentInfo> appointmentInfos) {
        this.appointmentInfos = appointmentInfos;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<String> getTouxiangs() {
        return touxiangs;
    }

    public void setTouxiangs(List<String> touxiangs) {
        this.touxiangs = touxiangs;
    }

    public List<Refresh> getRefreshs() {
        return refreshs;
    }

    public void setRefreshs(List<Refresh> refreshs) {
        this.refreshs = refreshs;
    }

    public List<OperaterInfo> getOperaterInfos() {
        return operaterInfos;
    }

    public void setOperaterInfos(List<OperaterInfo> operaterInfos) {
        this.operaterInfos = operaterInfos;
    }
}
