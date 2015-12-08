package com.dfsebook.mssage.operation;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.entity.OperaterInfo;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-22.
 */
public class DeleteAppointmentInfoByOperater extends BasicOperate {

    private Context context;

    private OperaterInfo operaterInfo;

    public DeleteAppointmentInfoByOperater(Context context, OperaterInfo operaterInfo) {
        this.context = context;
        this.operaterInfo = operaterInfo;
    }

    @Override
    protected void OperateDataSource() {
        List<AppointmentInfo> apps = DataSource.getData().getAppointmentInfos();
        for ( AppointmentInfo appointmentInfo : apps) {
            if(appointmentInfo.getCustomerId() == operaterInfo.getCustomerId()) {
                apps.remove(appointmentInfo);
                break;
            }
        }
        DataSource.getData().getOperaterInfos().remove(operaterInfo);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        String url = Config.SERVICE_URL + "deleteAppointmentInfo/" + operaterInfo.getCustomerId();
        url += "/" + Config.OPERATER_WAIT_DELETE;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals(Config.OPERATE_FAIL))
                            ErrorDialog.show(context);
                        else {
                            OperateDataSource();
                            NotifyOperaterChange(context);
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
    protected void NotifyOperaterChange(Context context) {
        super.NotifyOperaterChange(context);
    }
}
