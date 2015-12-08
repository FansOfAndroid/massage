package com.dfsebook.mssage.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.fragments.OperaterFragment;

/**
 * Created by Administrator on 15-11-8.
 */
public class OperaterDialog extends DialogFragment {

    private int requestType;

    public static OperaterDialog newInstance(int requestType){
        OperaterDialog operaterDialog = new OperaterDialog();
        Bundle args = new Bundle();
        args.putInt("requestType",requestType);
        operaterDialog.setArguments(args);
        return operaterDialog;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        if(getArguments() != null)
            this.requestType = getArguments().getInt("requestType");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.logo, null);
        builder.setView(view)
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getTargetFragment().onActivityResult(requestType,Activity.RESULT_OK, new Intent());
                    }
                })
                .setNegativeButton("等等", null);
        return builder.create();
    }
}
