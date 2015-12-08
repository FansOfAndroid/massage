package com.dfsebook.mssage.listener;

import android.app.Activity;
import android.os.Handler;

import com.android.volley.Response;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MSData;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.dialog.DownDialog;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.AppVersion;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.DownLoadApk;
import com.dfsebook.mssage.util.MD5Utils;
import com.google.gson.Gson;

/**
 * Created by Administrator on 15-9-23.
 */
public class GetAllDataListener implements Response.Listener<String> {

    private Activity activity;

    public GetAllDataListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(String s) {
        if(s.equals(Config.OPERATE_FAIL))
            ErrorDialog.show(activity);
        else {
            /*FileHelper fileHelper = new FileHelper(activity);
            if(fileHelper.hasSD()){
                fileHelper.deleteSDFile("data.txt");
                try {
                    fileHelper.createSDFile("data.txt");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                fileHelper.writeSDFile(s,"data.txt");
            }*/

            Gson gson = new Gson();
            MSData msData = gson.fromJson(s, MSData.class);
            MyApp.msData = msData;
            checkRegistered(msData);
            checkNewVersion(msData);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (DataSource.isNewVersion)
                        new DownDialog(activity, DataSource.isRegistered);
                    else
                        DownLoadApk.skip2LoadOrActivity(activity, DataSource.isRegistered);
                }
            },2000);
        }
    }

    private void checkRegistered(MSData msData){
        String ICCID = MD5Utils.getMD5String(MyApp.ICCID);
        String DIVICEID = MD5Utils.getMD5String(MyApp.DIVICEID);
        String PHONE = MD5Utils.getMD5String(MyApp.PHONE);
        if (msData == null) return;
        for(Customer customer : msData.getCustomers()){
            if(customer.getCustomerTel().equals(PHONE) ||
                    customer.getCustomerIMSI().equals(ICCID) ||
                    customer.getCustomerEmail().equals(DIVICEID)) {
                MyApp.isRegistered = true;
                DataSource.isRegistered = true;
                MyApp.currentCustomer =  customer;
                DataSource.currentCustomer = customer;
                return;
            }
        }
    }

    private void checkNewVersion(MSData msData){
        int code = MyApp.versionCode;
        AppVersion appVersion = msData.getAppVersion();
        if(code < appVersion.getVercode()) {
            MyApp.isNewVersion = true;
            DataSource.isNewVersion = true;
            MyApp.appVersion = appVersion;
            DataSource.appVersion = appVersion;
        }

    }
}
