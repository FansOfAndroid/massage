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
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

/**
 * Created by Administrator on 15-11-21.
 */
public class RevokeAppointmentInfo extends BasicOperate {

    private Context context;

    private int selectedIndex;

    private AppointmentInfo appointmentInfo;

    private AppointmentInfoAdapter adapter;

    public RevokeAppointmentInfo(Context context, int selectedIndex,
                                 AppointmentInfo appointmentInfo, AppointmentInfoAdapter adapter) {
        this.context = context;
        this.selectedIndex = selectedIndex;
        this.appointmentInfo = appointmentInfo;
        this.adapter = adapter;
    }

    @Override
    protected void OperateDataSource() {
        for (AppointmentInfo appInfo : DataSource.getData().getAppointmentInfos()){
            if(appInfo.getCustomerId() == appointmentInfo.getCustomerId()){
                appInfo.setAppointmentStation(Config.OPERATER_WAIT_DELETE);
            }
        }
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        int customerId = appointmentInfo.getCustomerId();
        String dateString = DataSource.transURLEncoderString(appointmentInfo.getAppointmentDate());
        String url = Config.SERVICE_URL + "revokeAppointmentInfo/" + customerId +"/" + dateString;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals(Config.OPERATE_FAIL))
                            ErrorDialog.show(context);
                        else {
                            OperateDataSource();
                            NotifyDataSetChange();
                            Toast.makeText(context,
                                    context.getResources().getString(R.string.cancel_request),
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
        appointmentInfo.setAppointmentStation(Config.OPERATER_WAIT_DELETE);
        adapter.notifyItemChanged(selectedIndex);
        super.NotifyDataSetChange();
    }

}
