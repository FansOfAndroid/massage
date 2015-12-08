package com.dfsebook.mssage.operation;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.adapter.ArrangeAdapter;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

/**
 * Created by Administrator on 15-11-22.
 */
public class UpdateLimit extends BasicOperate {

    private Context context;

    private int limitum;

    private int selectedIndex;

    private Appointment selectedAppointment;

    private ArrangeAdapter adapter;

    public UpdateLimit(Context context, int limitum,int selectedIndex,
                       Appointment selectedAppointment, ArrangeAdapter adapter) {
        this.limitum = limitum;
        this.selectedIndex = selectedIndex;
        this.context = context;
        this.selectedAppointment = selectedAppointment;
        this.adapter = adapter;
    }

    @Override
    protected void OperateDataSource() {
        for (Appointment appointment : DataSource.getAppointments()){
            if(appointment.toString().equals(selectedAppointment.toString()))
                appointment.setLimitNum(limitum);
        }
        super.OperateDataSource();
    }


    @Override
    public void OperateWebService() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "updateLimit/" + selectedAppointment.toString() + "/" + limitum,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals(Config.OPERATE_FAIL)){
                            ErrorDialog.show(context);
                        }
                        else {
                            OperateDataSource();
                            NotifyOperaterChange(context);
                        }
                    }
                },
                new AppErrorListener(context));
        MyApp.mRequestQueue.add(request);
        super.OperateWebService();
    }

    @Override
    protected void NotifyOperaterChange(Context context) {
        selectedAppointment.setLimitNum(limitum);
        adapter.notifyItemChanged(selectedIndex);
        super.NotifyOperaterChange(context);
    }
}