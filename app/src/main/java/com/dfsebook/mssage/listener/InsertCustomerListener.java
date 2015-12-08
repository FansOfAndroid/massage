package com.dfsebook.mssage.listener;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.Response;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.view.MainActivity;

/**
 * Created by Administrator on 15-10-8.
 */
public class InsertCustomerListener implements Response.Listener<String> {
    private Activity activity;

    private Customer customer;

    public InsertCustomerListener(Activity activity,Customer customer) {
        this.activity = activity;
        this.customer = customer;
    }
    @Override
    public void onResponse(String id) {
        if(id.equals("-1")){
            ErrorDialog.show(activity);
        }
        else {
            int customerId = Integer.valueOf(id);
            customer.setCustomerId(customerId);
            DataSource.currentCustomer = customer;
            DataSource.isRegistered = true;
            DataSource.getData().getCustomers().add(customer);
            Intent intent = new Intent();
            if(DataSource.reActivity == null)
                intent.setClass(activity,MainActivity.class);
            else {
                intent.setClass(activity, DataSource.reActivity.getClass());
            }
            activity.startActivity(intent);
            activity.finish();
        }

    }
}
