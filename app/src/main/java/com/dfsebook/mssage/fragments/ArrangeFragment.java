package com.dfsebook.mssage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.ArrangeAdapter;
import com.dfsebook.mssage.dialog.AddAppointmentDalog;
import com.dfsebook.mssage.dialog.AddLimitDialog;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.operation.ArrangeOperaterInfo;
import com.dfsebook.mssage.operation.InsertLimit;
import com.dfsebook.mssage.operation.UpdateLimit;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 15-11-6.
 */
public class ArrangeFragment extends Fragment implements ArrangeAdapter.OnItemClickLitener{

    private  List<Appointment> appointments;

    private Appointment selectedOne;

    private int selectedIndex;

    private ArrangeAdapter arrangeAdapter;

    private PopupWindow popupWindow;

    public static final int ADD_LIMIT = 10729;

    public static final int  ADD_APOINTMENT = 400729;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrange_listview,null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.arrnge_list);
        InitRecyclerView.initLinearLayoutVERTICAL(getContext(),recyclerView);
        appointments = DataSource.reGetAppointments();
        arrangeAdapter = new ArrangeAdapter(appointments);
        arrangeAdapter.setOnItemClickLitener(this);
        recyclerView.setAdapter(arrangeAdapter);
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        selectedOne = appointments.get(position);
        selectedIndex = position;
        showPopupWindow(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_LIMIT) {
            int num = data.getIntExtra("num",0);
            if(selectedOne.getLimitNum() == 0){
                InsertLimit insertLimit = new InsertLimit(getContext(),num,arrangeAdapter,selectedOne);
                insertLimit.OperateWebService();
            }
            else {
                UpdateLimit updateLimit = new UpdateLimit(getContext(),num,selectedIndex,selectedOne,arrangeAdapter);
                updateLimit.OperateWebService();
            }
        }
        if(requestCode == ADD_APOINTMENT){
            ArrangeOperaterInfo aoi = new ArrangeOperaterInfo(getContext(),data.getStringExtra("dateString"));
            aoi.OperateWebService();
        }
    }

    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(getContext()).inflate( R.layout.arrange_popup, null);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg));

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button limitSet = (Button) contentView.findViewById(R.id.limitset);
        Button appointmentAdd = (Button)contentView.findViewById(R.id.setAppoint);
        limitSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLimitDialog addLimitDialog = AddLimitDialog.newInstance(selectedOne);
                addLimitDialog.setTargetFragment(ArrangeFragment.this, ADD_LIMIT);
                addLimitDialog.show(getFragmentManager(), null);
                popupWindow.dismiss();
            }
        });
        appointmentAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAppointmentDalog addAppointmentDalog = AddAppointmentDalog.newInstance(selectedOne);
                addAppointmentDalog.setTargetFragment(ArrangeFragment.this,ADD_APOINTMENT);
                addAppointmentDalog.show(getFragmentManager(), null);
                popupWindow.dismiss();
            }
        });
    }

}
