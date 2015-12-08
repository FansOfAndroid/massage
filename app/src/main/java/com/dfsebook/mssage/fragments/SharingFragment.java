package com.dfsebook.mssage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.SharingAdapter;
import com.dfsebook.mssage.dialog.BackContentDialog;
import com.dfsebook.mssage.dialog.LoginDialog;
import com.dfsebook.mssage.entity.DialogProperty;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.operation.InsertQuestion;
import com.dfsebook.mssage.operation.InsertSharing;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 15-11-7.
 */
public class SharingFragment extends Fragment {

    private int tem = 0;

    private List<Sharing> sharings;

    private FloatingActionButton fab;

    private SharingAdapter sharingAdapter;

    private static final int REQUEST_SHARING = 33729;

    public static SharingFragment newInstance(List<Sharing> sharings){
        SharingFragment sharingFragment = new SharingFragment();
        Bundle args = new Bundle();
        args.putSerializable("sharings",(Serializable)sharings);
        sharingFragment.setArguments(args);
        return sharingFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.sharings = (List<Sharing>)getArguments().getSerializable("sharings");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tem++;
        System.out.println("pass:" + tem);
        View view = inflater.inflate(R.layout.activity_appinfo,null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.appList);
        fab = (FloatingActionButton)view.findViewById(R.id.appfabBtn);
        fab.setOnClickListener(new fabClickListener());
        InitRecyclerView.initLinearLayoutVERTICAL(getContext(), recyclerView);
        sharingAdapter = new SharingAdapter(sharings);
        recyclerView.setAdapter(sharingAdapter);
        return view;
    }

    private class fabClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(DataSource.currentCustomer == null){
                new LoginDialog(getActivity());
            }
            else {
                DialogProperty dialogProperty = new DialogProperty(REQUEST_SHARING, "分享", "算了", "输入分享内容");
                BackContentDialog backContentDialog = BackContentDialog.newInstance(dialogProperty);
                backContentDialog.setTargetFragment(SharingFragment.this, REQUEST_SHARING);
                backContentDialog.show(getFragmentManager(), "");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SHARING){
            InsertSharing insertSharing = new InsertSharing(getContext(),data.getStringExtra("content"),sharingAdapter);
            insertSharing.OperateWebService();
        }
    }


}
