package com.dfsebook.mssage.operation;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-28.
 */
public class InsertTouxiang extends BasicOperate {

    private String touXiang;

    private Context context;

    private boolean newTouxiang;

    private List<String> touxiangs;

    public InsertTouxiang(Context context,String touXiang,boolean newTouxiang,List<String> touxiangs) {
        this.context = context;
        this.touXiang = touXiang;
        this.newTouxiang = newTouxiang;
        this.touxiangs = touxiangs;
    }

    @Override
    protected void OperateDataSource() {
        DataSource.getData().getTouxiangs().add(0,touXiang);
        newTouxiang = true;
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "insertTx/" + touXiang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(Config.OPERATE_SUCCESS.equals(s)){
                            OperateDataSource();
                            NotifyDataSetChange();
                        }
                        else
                            ErrorDialog.show(context);
                    }
                },new AppErrorListener(context));
        MyApp.mRequestQueue.add(request);
        super.OperateWebService();
    }

    @Override
    protected void NotifyDataSetChange() {
        touxiangs.add(0,touXiang);
        newTouxiang = true;
        super.NotifyDataSetChange();
    }
}
