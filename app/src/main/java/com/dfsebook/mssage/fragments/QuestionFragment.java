package com.dfsebook.mssage.fragments;

import android.app.Activity;
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
import com.dfsebook.mssage.adapter.QuestionAdapter;
import com.dfsebook.mssage.dialog.BackContentDialog;
import com.dfsebook.mssage.dialog.LoginDialog;
import com.dfsebook.mssage.entity.DialogProperty;
import com.dfsebook.mssage.entity.Question;
import com.dfsebook.mssage.fold.DetailAnimViewGroup;
import com.dfsebook.mssage.operation.InsertQuestion;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;

/**
 * Created by Administrator on 15-11-7.
 */
public class QuestionFragment extends Fragment implements QuestionAdapter.OnItemClickLitener{

    private OnFragmentInteractionListener mListener;

    private QuestionAdapter questionAdapter;

    private FloatingActionButton fab;

    public static final int REQUEST_QUESTION = 100729;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_appinfo,null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.appList);
        InitRecyclerView.initLinearLayoutVERTICAL(getContext(), recyclerView);
        questionAdapter = new QuestionAdapter();
        recyclerView.setAdapter(questionAdapter);
        fab = (FloatingActionButton)view.findViewById(R.id.appfabBtn);
        DetailAnimViewGroup wrapper = new DetailAnimViewGroup(inflater.getContext(), view, 0);
        questionAdapter.setOnItemClickLitener(this);
        fab.setOnClickListener(new fabClickListener());
        return wrapper;
    }

    private class fabClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(DataSource.currentCustomer != null) {
                DialogProperty dialogProperty = new DialogProperty(REQUEST_QUESTION, "提问", "再说", "输入您的问题");
                BackContentDialog backContentDialog = BackContentDialog.newInstance(dialogProperty);
                backContentDialog.setTargetFragment(QuestionFragment.this, REQUEST_QUESTION);
                backContentDialog.show(getFragmentManager(), "");
            }
            else {
                new LoginDialog(getActivity());
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(View view,Question question) {
        if (null != mListener) {
            mListener.onFragmentInteraction(view,question);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(View view,Question question);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_QUESTION){
            InsertQuestion insertQuestion = new InsertQuestion(getContext(),
                    data.getStringExtra("content"),questionAdapter);
            insertQuestion.OperateWebService();
        }
    }
}
