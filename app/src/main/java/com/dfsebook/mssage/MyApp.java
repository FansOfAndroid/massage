package com.dfsebook.mssage;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dfsebook.mssage.entity.AppVersion;
import com.dfsebook.mssage.entity.Customer;

/**
 * Created by Administrator on 15-10-8.
 */
public class MyApp  extends Application {

    public static Context appContext;

    public static RequestQueue mRequestQueue;

    public static String ICCID;

    public static String PHONE;

    public static String DIVICEID;

    public static int versionCode;

    public static MSData msData;

    public static boolean isNewVersion;

    public static AppVersion appVersion;

    public static boolean isRegistered;

    public static Customer currentCustomer;

    @Override
    public void onCreate() {
        appContext = this;
        mRequestQueue = Volley.newRequestQueue(this);
        getICCID();
        getPhone();
        getDIVICEID();
        getVersion();
        super.onCreate();
    }

    private void getDIVICEID() {
        try {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            DIVICEID = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            DIVICEID = " ";
        }
    }

    private void getICCID(){
        try {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            ICCID = tm.getSimSerialNumber();
//            ICCID = "12345678987654321";
        }
        catch (Exception e){
            e.printStackTrace();
            ICCID = " ";
        }
    }

    private void getPhone(){
        try {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            PHONE = tm.getLine1Number();
        }
        catch (Exception e){
            e.printStackTrace();
            PHONE = " ";
        }
    }

    private void getVersion(){
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = 1000000;
        }
    }
}
