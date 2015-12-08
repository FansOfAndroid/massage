package com.dfsebook.mssage.util;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MSData;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.entity.AppVersion;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.entity.Limit;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * Created by Administrator on 15-11-24.
 */
public class DataSource {

    private static List<Appointment> appointments;

    public static MSData getData(){
        if(MyApp.msData != null)
            return MyApp.msData;
        else {
            FileHelper fileHelper = new FileHelper(MyApp.appContext);
            String gsonString = fileHelper.readSDFile("data.txt");
            Gson gson = new Gson();
            MSData data = gson.fromJson(gsonString,MSData.class);
            MyApp.msData = data;
            return data;
        }
    }

    public static void writeFileToSDCard(){
        MSData msData = getData();
        Gson gson = new Gson();
        FileHelper fileHelper = new FileHelper(MyApp.appContext);
        if(fileHelper.hasSD()) {
            fileHelper.deleteSDFile("data.txt");
            try {
                fileHelper.createSDFile("data.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileHelper.writeSDFile(gson.toJson(msData), "data.txt");
        }
    }

    public static String transURLEncoderString(String s) {
        try {
            s = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            s = "";
        }
        return s;
    }

    public static String transURLDecoderString(String s) {
        try {
            s = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            s = "";
        }
        return s;
    }

    public static Customer currentCustomer;

    public static boolean isNewVersion;

    public static AppVersion appVersion;

    public static boolean isRegistered;

    public static Activity reActivity;

    public static List<Appointment> getAppointments() {
        if(appointments != null)
            return appointments;
        else {
            return getAppments();
        }
    }

    public static List<Appointment> reGetAppointments(){
        return getAppments();
    }

    private static List<Appointment> getAppments() {
        List<Appointment> appointmentList = new ArrayList<>();
        formBasicAppointment(appointmentList);
        addPointmentInfoToAppointment(appointmentList);
        addLimitToAppointment(appointmentList);
        return appointmentList;
    }

    private static Appointment getAppointment(Calendar calendar){
        Lunar lunar = new Lunar(calendar);
        Appointment appointment = new Appointment();
        appointment.setChineseWeekDay(lunar.Date2ChineseWeek());
        if(lunar.Date2ChineseWeek().equals("星期六") || lunar.Date2ChineseWeek().equals("星期日"))
            appointment.setWeekend(true);
        appointment.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
        appointment.setMonth(String.valueOf(format(calendar.get(Calendar.MONTH) + 1)) + "月");
        appointment.setDay(String.valueOf(format(calendar.get(Calendar.DAY_OF_MONTH))));
        return appointment;
    }

    private static void formBasicAppointment(List<Appointment> apps){
        Calendar calendar =  new GregorianCalendar();
        calendar.setTime(new Date());
        Appointment appointment ;
        for(int i = 0; i < Config.APPOINTMENT_SHOW_DAYS; i++){
            if(i == 0){
                appointment = getAppointment(calendar);
                appointment.setShowHeader(true);
            }
            else {
                calendar.add(Calendar.DATE, 1);
                appointment = getAppointment(calendar);
            }
            apps.add(appointment);
        }
    }

    private static void addPointmentInfoToAppointment(List<Appointment> apps){
        List<AppointmentInfo> appinfos;
        String tem,temp;
        for(Appointment appointment : apps){
            appinfos = new ArrayList<>();
            int rem = 0;
            for (AppointmentInfo appointmentInfo : getData().getAppointmentInfos()){
                tem = appointmentInfo.getAppointmentDate().substring(0, 10);
                temp = appointment.toString();
                if(tem.equals(temp)){
                    if(appointmentInfo.getAppointmentStation() != 0) {
                        rem++;
                    }
                    appinfos.add(appointmentInfo);
                }
                if(appointmentInfo.getCustomerId() == currentCustomer.getCustomerId()){
                    appointment.setThere(true);
                    appointmentInfo.setOwner(true);
                }
            }
            appointment.setRequestNum(appinfos.size());
            appointment.setOcupied(rem);
            appointment.setAppointmentInfos(appinfos);
        }
    }

    private static void addLimitToAppointment(List<Appointment> apps){
        for (Limit limit : getData().getLimits()){
            for (Appointment app : apps){
                if (app.toString().equals(limit.getDate())){
                    app.setLimitNum(limit.getCount());
                }
            }
        }
    }

    private static String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    public static boolean isAppointmented() {
        if (currentCustomer == null) return false;
        for (AppointmentInfo appointmentInfo : getData().getAppointmentInfos()) {
            if (appointmentInfo.getCustomerId() == currentCustomer.getCustomerId()) {
                return true;
            }
        }
        return false;
    }

}
