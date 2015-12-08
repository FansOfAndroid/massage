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
import com.dfsebook.mssage.entity.Limit;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-23.
 */
public class InsertLimit extends BasicOperate {

    private Context context;

    private int limitnum;

    private Appointment selectedAppointment;

    private ArrangeAdapter adapter;

    private Limit limit;

    public InsertLimit(Context context, int limitnum, ArrangeAdapter adapter,
                       Appointment selectedAppointment) {
        this.context = context;
        this.limitnum = limitnum;
        this.adapter = adapter;
        this.selectedAppointment = selectedAppointment;
        limit = formLimit(limitnum,selectedAppointment.toString());
    }

    @Override
    protected void OperateDataSource() {
        DataSource.getData().getLimits().add(limit);
        for (Appointment appointment : DataSource.getAppointments()){
            if(appointment.toString().equals(selectedAppointment.toString())) {
                appointment.setLimitNum(limitnum);
            }
        }
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "insertLimit/" + limit.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals(Config.OPERATE_FAIL))
                            ErrorDialog.show(context);
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
        adapter.notifyDataSetChanged();
        super.NotifyOperaterChange(context);
    }

    private Limit formLimit(int limitnum,String dateString){
        Limit limit = new Limit();
        limit.setCount(limitnum);
        limit.setDate(dateString);
        limit.setReal(0);
        return limit;
    }
}
