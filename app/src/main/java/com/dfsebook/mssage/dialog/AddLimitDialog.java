package com.dfsebook.mssage.dialog;

import android.app.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.ArrangeAdapter;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.fragments.ArrangeFragment;

/**
 * Created by Administrator on 15-11-10.
 */
public class AddLimitDialog extends DialogFragment {

    private Appointment appointment;

    private NumberPicker numberPicker;

    public static AddLimitDialog newInstance(Appointment appointment){
        AddLimitDialog addLimitDialog = new AddLimitDialog();
        Bundle args = new Bundle();
        args.putSerializable("appointment", appointment);
        addLimitDialog.setArguments(args);
        return addLimitDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        if(getArguments() != null){
            this.appointment = (Appointment)getArguments().getSerializable("appointment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(DialogFragment.STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.add_limit,null);
        numberPicker = (NumberPicker)view.findViewById(R.id.setCount);
        numberPicker.setMaxValue(Config.LIMIT_NUM);
        numberPicker.setMinValue(1);
        numberPicker.setValue(appointment.getLimitNum());
        Button sure = (Button)view.findViewById(R.id.set_count);
        Button cancel = (Button)view.findViewById(R.id.set_cancel);
        sure.setOnClickListener(new SetCountListener());
        cancel.setOnClickListener(new CancelListener());
        return view;
    }

    private class SetCountListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("num", numberPicker.getValue());
            getTargetFragment().onActivityResult(ArrangeFragment.ADD_LIMIT, Activity.RESULT_OK, intent);
            AddLimitDialog.this.dismiss();
        }
    }

    private class CancelListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            AddLimitDialog.this.dismiss();
        }
    }
}
