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
import com.dfsebook.mssage.adapter.ReplyAdapter;
import com.dfsebook.mssage.dialog.BackContentDialog;
import com.dfsebook.mssage.dialog.LoginDialog;
import com.dfsebook.mssage.entity.DialogProperty;
import com.dfsebook.mssage.entity.Reply;
import com.dfsebook.mssage.fold.DetailAnimViewGroup;
import com.dfsebook.mssage.operation.InserReply;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 15-11-7.
 */
public class ReplyFragment extends Fragment {

    private List<Reply> replies;

    private int questionId;

    private ReplyAdapter replyAdapter;

    private FloatingActionButton fab;

    public static final int REQUEST_REPLY = 200729;

    public static ReplyFragment newInstance(List<Reply> replies,int questionId){
        ReplyFragment replyFragment = new ReplyFragment();
        Bundle args = new Bundle();
        args.putInt("questionId",questionId);
        args.putSerializable("replies",(Serializable)replies);
        replyFragment.setArguments(args);
        return  replyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.questionId = getArguments().getInt("questionId");
            this.replies = (List<Reply>)getArguments().getSerializable("replies");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_appinfo,null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.appList);
        InitRecyclerView.initLinearLayoutVERTICAL(getContext(),recyclerView);
        replyAdapter = new ReplyAdapter(replies);
        recyclerView.setAdapter(replyAdapter);
        fab = (FloatingActionButton)view.findViewById(R.id.appfabBtn);
        DetailAnimViewGroup wrapper = new DetailAnimViewGroup(inflater.getContext(), view, 0);
        fab.setOnClickListener(new fabClickListener());
        wrapper.setReversed(false);
        return wrapper;
    }

    private class fabClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(DataSource.currentCustomer != null) {
                DialogProperty dialogProperty = new DialogProperty(REQUEST_REPLY, "回答", "再想想", "输入您的回答");
                BackContentDialog backContentDialog = BackContentDialog.newInstance(dialogProperty);
                backContentDialog.setTargetFragment(ReplyFragment.this, REQUEST_REPLY);
                backContentDialog.show(getFragmentManager(), "");
            }
            else {
                new LoginDialog(getActivity());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String replyContent = data.getStringExtra("content");
        if(requestCode == REQUEST_REPLY){
            InserReply inserReply = new InserReply(getContext(),replyContent,questionId,replyAdapter);
            inserReply.OperateWebService();
        }
    }
}
