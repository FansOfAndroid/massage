package com.dfsebook.mssage.operation;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.entity.OperaterInfo;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class UpdateAppointmentInfo extends BasicOperate {

    private Context context;

    private OperaterInfo operaterInfo;

    public UpdateAppointmentInfo(Context context, OperaterInfo operaterInfo) {
        this.context = context;
        this.operaterInfo = operaterInfo;
    }

    @Override
    protected void OperateDataSource() {
        for ( AppointmentInfo appointmentInfo : DataSource.getData().getAppointmentInfos()) {
            if(appointmentInfo.getCustomerId() == operaterInfo.getCustomerId()) {
                appointmentInfo.setAppointmentDate(operaterInfo.getDateString());
                appointmentInfo.setAppointmentStation(Config.OPERATER_APPOINTMENTED);
            }
        }
        DataSource.getData().getOperaterInfos().remove(operaterInfo);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        String dateString = DataSource.transURLEncoderString(operaterInfo.getDateString());
        int customerId = operaterInfo.getCustomerId();
        int station = operaterInfo.getInfoType();
        String url = Config.SERVICE_URL + "updateAppointmentInfo/" + customerId;
        url +=  "/" + dateString +"/" + station;
        StringRequest request = new StringRequest(Request.Method.POST, url,
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
}
