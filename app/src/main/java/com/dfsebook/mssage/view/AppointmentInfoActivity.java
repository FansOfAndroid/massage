package com.dfsebook.mssage.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.AppointmentInfoAdapter;
import com.dfsebook.mssage.dialog.ChangeAppointmentDialog;
import com.dfsebook.mssage.dialog.LoginDialog;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.operation.ChangAppointmentInfo;
import com.dfsebook.mssage.operation.DeleteAppiontmentInfo;
import com.dfsebook.mssage.operation.InsertAppointmentInfo;
import com.dfsebook.mssage.operation.RevokeAppointmentInfo;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;

import java.util.List;

public class AppointmentInfoActivity extends AppCompatActivity implements ChangeAppointmentDialog.AppointmentDialogListener{

    private Appointment appointment;

    private List<AppointmentInfo> appointmentInfos;

    private AppointmentInfo selectedAppointmentInfo;

    private int selectedIndex;

    private AppointmentInfoAdapter adapter;

    private FloatingActionButton fab;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        if(DataSource.isAppointmented())
            fab.setVisibility(View.GONE);
        fab.setOnClickListener(new addAppointmentInfoClick());
    }

    private void init(){
        fab = (FloatingActionButton)findViewById(R.id.appfabBtn);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.appList);
        InitRecyclerView.initLinearLayoutVERTICAL(this, recyclerView);
        Intent intent = getIntent();
        appointment = (Appointment)intent.getSerializableExtra("appointment");
        appointmentInfos = appointment.getAppointmentInfos();
        adapter = new AppointmentInfoAdapter(appointmentInfos);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new adapterClickListener());
    }

    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.appointmentinfo_popup, null);
        popupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button cancel = (Button) contentView.findViewById(R.id.delete_set);
        Button update = (Button) contentView.findViewById(R.id.update_set);
        if(selectedAppointmentInfo.getAppointmentStation() != 1)
            update.setEnabled(false);
        cancel.setOnClickListener(new cancelClickListener());
        update.setOnClickListener(new updateClickListener());
    }

    private class adapterClickListener implements AppointmentInfoAdapter.OnItemClickListener{
        @Override
        public void onItemClick(View view, int position) {
            selectedAppointmentInfo = appointmentInfos.get(position);
            if(!selectedAppointmentInfo.isOwner()) return;
            selectedIndex = position;
            showPopupWindow(view);
        }
    }

    private class cancelClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(selectedAppointmentInfo.getAppointmentStation() == Config.OPERATER_WAIT_IN) {
                DeleteAppiontmentInfo da = new DeleteAppiontmentInfo(AppointmentInfoActivity.this,
                        selectedAppointmentInfo,appointmentInfos,adapter);
                da.OperateWebService();
            }
            if(selectedAppointmentInfo.getAppointmentStation() == Config.OPERATER_APPOINTMENTED){
                RevokeAppointmentInfo ra = new RevokeAppointmentInfo(AppointmentInfoActivity.this,
                        selectedIndex,selectedAppointmentInfo,adapter);
                ra.OperateWebService();
            }
            popupWindow.dismiss();
        }
    }

    private class updateClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ChangeAppointmentDialog changeAppointmentDialog = ChangeAppointmentDialog.newInstance(appointment);
            changeAppointmentDialog.show(getFragmentManager(),null);
            popupWindow.dismiss();
        }
    }

    private class addAppointmentInfoClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(DataSource.currentCustomer != null) {
                InsertAppointmentInfo insertAppointmentInfo = new InsertAppointmentInfo(AppointmentInfoActivity.this,
                        appointment.toString(),selectedIndex,fab,appointmentInfos,adapter);
                insertAppointmentInfo.OperateWebService();
            } else
                new LoginDialog(AppointmentInfoActivity.this);
        }
    }
    @Override
    public void back(String dateString) {
        ChangAppointmentInfo changAppointmentInfo = new ChangAppointmentInfo(AppointmentInfoActivity.this,
                dateString,selectedIndex,selectedAppointmentInfo,adapter);
        changAppointmentInfo.OperateWebService();
    }
}
