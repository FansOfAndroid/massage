package com.dfsebook.mssage.operation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.adapter.SharingAdapter;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class InsertSharing extends BasicOperate {

    private Context context;

    private String sharingContent;

    private Sharing sharing;

    private SharingAdapter adapter;

    public InsertSharing(Context context, String sharingContent,SharingAdapter adapter) {
        this.context = context;
        this.sharingContent = sharingContent;
        this.adapter = adapter;
        sharing = formSharing(sharingContent);
    }

    @Override
    protected void OperateDataSource() {
        DataSource.getData().getSharings().add(sharing);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "insertSharing/" + sharing.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals(Config.OPERATE_FAIL)){
                            ErrorDialog.show(context);
                        }
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
        adapter.addfresh(sharing);
        super.NotifyDataSetChange();
    }

    private Sharing formSharing(String content){
        content = DataSource.transURLDecoderString(content);
        Sharing sharing = new Sharing();
        sharing.setCustomerId(DataSource.currentCustomer.getCustomerId());
        sharing.setCustomerName(DataSource.currentCustomer.getCustomerName());
        sharing.setCustomerPhoto(DataSource.currentCustomer.getCustomerPhoto());
        sharing.setSharingContent(content);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sharing.setSharingDate(simpleDateFormat.format(new Date()));
        sharing.setNewSharing(true);
        sharing.setSharingTitle(Config.OPERATER_SHARING);
        sharing.setSelfShow(false);
        return sharing;
    }
}
