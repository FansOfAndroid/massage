package com.dfsebook.mssage.operation;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.adapter.OperaterAdapter;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.OperaterInfo;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-22.
 */
public class UpdateSharing extends BasicOperate {

    private Context context;

    private OperaterInfo operaterInfo;

    private List<OperaterInfo> operaterInfos;

    private OperaterAdapter adapter;

    public UpdateSharing(Context context, OperaterInfo operaterInfo,
                         List<OperaterInfo> operaterInfos, OperaterAdapter adapter) {
        this.context = context;
        this.operaterInfo = operaterInfo;
        this.operaterInfos = operaterInfos;
        this.adapter = adapter;
    }

    @Override
    protected void OperateDataSource() {
        for (Sharing sharing : DataSource.getData().getSharings()) {
            if(sharing.getSharingId() == operaterInfo.getSharingId())
                sharing.setSharingTitle(1);
        }
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "updateSharing/" + operaterInfo.getSharingId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals(Config.OPERATE_FAIL))
                            ErrorDialog.show(context);
                        else {
                            OperateDataSource();
                            NotifyDataSetChange();
                        }
                    }
                },
                new AppErrorListener(context));
        MyApp.mRequestQueue.add(request);
        super.OperateWebService();
    }

    @Override
    protected void NotifyDataSetChange() {
        operaterInfos.remove(operaterInfo);
        adapter.notifyDataSetChanged();
        super.NotifyDataSetChange();
    }
}
