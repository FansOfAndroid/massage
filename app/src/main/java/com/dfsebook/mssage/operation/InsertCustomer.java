package com.dfsebook.mssage.operation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.listener.InsertCustomerListener;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.MD5Utils;
import com.dfsebook.mssage.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 15-11-28.
 */
public class InsertCustomer extends BasicOperate {

    private Activity activity;

    private String name;

    private String code;

    private String tx;

    private Customer customer;

    public InsertCustomer(Activity activity, String name, String code, String tx) {
        this.activity = activity;
        this.name = name;
        this.code = code;
        this.tx = tx;
        customer = formCustomer();
    }

    @Override
    protected void OperateDataSource() {
        DataSource.getData().getCustomers().add(customer);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        String url = Config.SERVICE_URL + "insertCustomer/" + customer.toString();
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new InsertCustomerListener(activity,customer),
                new AppErrorListener(activity));
        MyApp.mRequestQueue.add(req);
        super.OperateWebService();
    }

    private Customer formCustomer(){
        Customer customer = new Customer();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        customer.setCustomerIMSI(MD5Utils.getMD5String(MyApp.ICCID));
        customer.setCustomerLastLogT(dateFormat.format(date));
        customer.setCustomerName(name);
        customer.setCustomerPwd(MD5Utils.getMD5String(code));
        customer.setCustomerRegTime(dateFormat.format(date));
        customer.setCustomerTel(MyApp.PHONE);
        customer.setCustomerLogTime(1);
        customer.setCustomerClass(1);
        customer.setCustomerEmail(MD5Utils.getMD5String(MyApp.DIVICEID));
        customer.setCustomerPhoto(tx);
        return customer;
    }
}
