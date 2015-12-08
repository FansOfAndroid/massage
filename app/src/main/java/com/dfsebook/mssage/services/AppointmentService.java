package com.dfsebook.mssage.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.view.AppointmentInfoActivity;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 15-10-23.
 */
public class AppointmentService extends Service {

    private static final int NOTIFICATION_FLAG = 1;

    private List<AppointmentInfo> appointmentInfos;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("start");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
//        getCustomer();
    }

    private void getCustomer(){
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "getCustomerAndAppointmentInfo/" + MyApp.ICCID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals(Config.OPERATE_FAIL)) {
//                            ErrorDialog.show(getApplicationContext());
                        }
                        else {
                            Gson gson = new Gson();
//                            LimitsAndAppointmentInfos caa = gson.fromJson(s,LimitsAndAppointmentInfos.class);
//                            appointmentInfos = caa.getAppointmentInfos();
/*                            if(MyApp.currentCustomer == null)
                                MyApp.currentCustomer = caa.getCustomer();*/
                            if(DataSource.currentCustomer.getCustomerClass() == 3)
                                createNotification();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        MyApp.mRequestQueue.add(request);
    }

    private void createNotification(){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification();
        notification.icon = R.drawable.logo;
        notification.tickerText = "You have a new Notice!";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notice_item);
        remoteViews.setTextViewText(R.id.notice_content,"hello");
        notification.contentView = remoteViews;
        Intent intent = new Intent(getBaseContext(), AppointmentInfoActivity.class);
        intent.putExtra("infos",(Serializable)appointmentInfos);
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),0,intent,0);
        notification.contentIntent = pendingIntent;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
        manager.notify(NOTIFICATION_FLAG,notification);
    }
}
