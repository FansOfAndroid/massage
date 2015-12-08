package com.dfsebook.mssage.view;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.listener.GetAllDataListener;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(savedInstanceState == null) {
            StringRequest request = new StringRequest(Request.Method.POST,
                    Config.SERVICE_URL + "getAllDatas",
                    new GetAllDataListener(StartActivity.this),
                    new AppErrorListener(StartActivity.this));
            MyApp.mRequestQueue.add(request);
        }
    }
}
