package com.dfsebook.mssage.operation;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-24.
 */
public class ArrangeOperaterInfo extends BasicOperate {

    private Context context;

    private AppointmentInfo appointmentInfo;

    public ArrangeOperaterInfo(Context context, String dateString) {
        this.context = context;
        this.appointmentInfo = formAppointmentInfo(dateString);
    }

    @Override
    protected void OperateDataSource() {
        DataSource.getData().getAppointmentInfos().add(appointmentInfo);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        String url = Config.SERVICE_URL + "insertAppointmentInfo/" + appointmentInfo.toString();
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals(Config.OPERATE_SUCCESS)) {
                            OperateDataSource();
                            NotifyOperaterChange(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context)
                                    .setTitle("系统提示")
                                    .setMessage("您的预约申请已经上传，等待医师确认。")
                                    .setPositiveButton("确定", null)
                                    .show();
                        }
                        else
                            ErrorDialog.show(context);
                    }
                },
                new AppErrorListener(context));
        MyApp.mRequestQueue.add(request);
        super.OperateWebService();
    }

    private AppointmentInfo formAppointmentInfo(String dateString){
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        appointmentInfo.setCustomerId(DataSource.currentCustomer.getCustomerId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        appointmentInfo.setAppointmentDate(dateString + " " + simpleDateFormat.format(new Date()));
        appointmentInfo.setAppointmentStation(Config.OPERATER_APPOINTMENTED);
        appointmentInfo.setOwner(true);
        appointmentInfo.setCustomerName(DataSource.currentCustomer.getCustomerName());
        appointmentInfo.setCustomerPhoto(DataSource.currentCustomer.getCustomerPhoto());
        return appointmentInfo;
    }
}
