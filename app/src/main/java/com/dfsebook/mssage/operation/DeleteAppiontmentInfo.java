package com.dfsebook.mssage.operation;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.AppointmentInfoAdapter;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class DeleteAppiontmentInfo extends BasicOperate {

    private Context context;

    private AppointmentInfo appointmentInfo;

    private List<AppointmentInfo> appointmentInfos;

    private AppointmentInfoAdapter adapter;

    public DeleteAppiontmentInfo(Context context, AppointmentInfo appointmentInfo,
                                 List<AppointmentInfo> appointmentInfos, AppointmentInfoAdapter adapter) {
        this.context = context;
        this.appointmentInfo = appointmentInfo;
        this.appointmentInfos = appointmentInfos;
        this.adapter = adapter;
    }

    @Override
    protected void OperateDataSource() {
        List<AppointmentInfo> apps = DataSource.getData().getAppointmentInfos();
        for (AppointmentInfo appInfo : apps){
            if (appInfo.getCustomerId() == appointmentInfo.getCustomerId())
                apps.remove(appInfo);
        }
        DataSource.getData().setAppointmentInfos(apps);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        String url = Config.SERVICE_URL + "deleteAppointmentInfo/" + appointmentInfo.getCustomerId();
        url += "/" + Config.OPERATER_WAIT_IN;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals(Config.OPERATE_FAIL))
                            ErrorDialog.show(context);
                        else {
                            OperateDataSource();
                            NotifyDataSetChange();
                            Toast.makeText(context,
                                    context.getResources().getString(R.string.cancel_success),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new AppErrorListener(context));
        MyApp.mRequestQueue.add(request);
        super.OperateWebService();
    }

    @Override
    protected void NotifyDataSetChange() {
        appointmentInfos.remove(appointmentInfo);
        adapter.notifyDataSetChanged();
        super.NotifyDataSetChange();
    }

}
