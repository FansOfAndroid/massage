package com.dfsebook.mssage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.CustomerAdapter;
import com.dfsebook.mssage.adapter.OperaterAdapter;
import com.dfsebook.mssage.dialog.CustomerAlertDialog;
import com.dfsebook.mssage.dialog.OperaterDialog;
import com.dfsebook.mssage.dialog.SignDialog;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.OperaterInfo;
import com.dfsebook.mssage.operation.DeleteAppointmentInfoByOperater;
import com.dfsebook.mssage.operation.UpdateAppointmentInfo;
import com.dfsebook.mssage.operation.UpdateQuestion;
import com.dfsebook.mssage.operation.UpdateReply;
import com.dfsebook.mssage.operation.UpdateSharing;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 15-11-6.
 */
public class OperaterFragment extends Fragment {

    private List<OperaterInfo> operaterInfos;

    private OperaterInfo operaterInfo;

    private OperaterAdapter operaterAdapter;

    public static final int REQUEST_SIGN = 10729;

    public static final int REQUEST_REVOKE = 0X110;

    public static final int REQUEST_CHANGE = 729;

    public static final int REQUEST_QUESTION = 100729;

    public static final int REQUEST_REPLY = 200729;

    public static final int REQUEST_SHARING = 300729;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview,null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.showList);
        InitRecyclerView.initLinearLayoutVERTICAL(getContext(),recyclerView);
        operaterInfos = DataSource.getData().getOperaterInfos();
        operaterAdapter = new OperaterAdapter(operaterInfos);
        recyclerView.setAdapter(operaterAdapter);

        operaterAdapter.setOnItemClickLitener(new CustomerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                operaterInfo = operaterInfos.get(position);
                if (operaterInfo.getInfoType() == Config.OPERATER_WAIT_IN) {
                    SignDialog signDialog = new SignDialog();
                    signDialog.setTargetFragment(OperaterFragment.this, REQUEST_SIGN);
                    signDialog.show(getFragmentManager(), null);
                }
                if (operaterInfo.getInfoType() == Config.OPERATER_WAIT_DELETE) {
                    OperaterDialog operaterDialog = OperaterDialog.newInstance(REQUEST_REVOKE);
                    operaterDialog.setTargetFragment(OperaterFragment.this, REQUEST_REVOKE);
                    operaterDialog.show(getFragmentManager(), "");
                }
                if (operaterInfo.getInfoType() == Config.OPERATER_WAIT_CHANGE) {
                    OperaterDialog operaterDialog = OperaterDialog.newInstance(REQUEST_CHANGE);
                    operaterDialog.setTargetFragment(OperaterFragment.this, REQUEST_CHANGE);
                    operaterDialog.show(getFragmentManager(), "");
                }
                if (operaterInfo.getInfoType() == Config.OPERATER_QUESTION) {
                    OperaterDialog operaterDialog = OperaterDialog.newInstance(REQUEST_QUESTION);
                    operaterDialog.setTargetFragment(OperaterFragment.this, REQUEST_QUESTION);
                    operaterDialog.show(getFragmentManager(), "");
                }
                if (operaterInfo.getInfoType() == Config.OPERATER_REPLY) {
                    OperaterDialog operaterDialog = OperaterDialog.newInstance(REQUEST_REPLY);
                    operaterDialog.setTargetFragment(OperaterFragment.this, REQUEST_REPLY);
                    operaterDialog.show(getFragmentManager(), "");
                }
                if (operaterInfo.getInfoType() == Config.OPERATER_SHARING) {
                    OperaterDialog operaterDialog = OperaterDialog.newInstance(REQUEST_SHARING);
                    operaterDialog.setTargetFragment(OperaterFragment.this, REQUEST_SHARING);
                    operaterDialog.show(getFragmentManager(), "");
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SIGN){
            String dateString = operaterInfo.getDateString().substring(0,10) + data.getStringExtra("time");
            operaterInfo.setDateString(dateString);
            operaterInfo.setInfoType(Config.OPERATER_WAIT_IN);
            UpdateAppointmentInfo updateAppointmentInfo = new UpdateAppointmentInfo(getContext(),operaterInfo);
            updateAppointmentInfo.OperateWebService();
        }
        if(requestCode == REQUEST_REVOKE){
            DeleteAppointmentInfoByOperater dab = new DeleteAppointmentInfoByOperater(getContext(),operaterInfo);
            dab.OperateWebService();
        }
        if(requestCode == REQUEST_CHANGE){
            operaterInfo.setDateString(operaterInfo.getContent());
            operaterInfo.setInfoType(Config.OPERATER_WAIT_CHANGE);
            operaterInfo.setContent("");
            UpdateAppointmentInfo updateAppointmentInfo = new UpdateAppointmentInfo(getContext(),operaterInfo);
            updateAppointmentInfo.OperateWebService();
        }
        if(requestCode == REQUEST_QUESTION){
            UpdateQuestion updateQuestion = new UpdateQuestion(getContext(),operaterInfo,
                    operaterInfos,operaterAdapter);
            updateQuestion.OperateWebService();
        }
        if (requestCode == REQUEST_REPLY){
            UpdateReply updateReply = new UpdateReply(getContext(),operaterInfo,
                    operaterInfos,operaterAdapter);
            updateReply.OperateWebService();
        }
        if (requestCode == REQUEST_SHARING){
            UpdateSharing updateSharing = new UpdateSharing(getContext(),operaterInfo,
                    operaterInfos,operaterAdapter);
            updateSharing.OperateWebService();
        }
    }

}
