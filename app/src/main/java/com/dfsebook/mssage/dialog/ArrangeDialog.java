package com.dfsebook.mssage.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.fragments.ArrangeFragment;
import com.dfsebook.mssage.util.InitRecyclerView;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Administrator on 15-11-9.
 */
public class ArrangeDialog extends DialogFragment implements NumberPicker.Formatter{

    private Appointment appointment ;

    private NumberPicker limitSelector;

    private NumberPicker userSelector;

    private NumberPicker hour_Selector;

    private NumberPicker minute_Selector;

    private Button setLimit;

    private Button addAppointment;

    private Button cancelAppointment;

    private String[] names;

    public static ArrangeDialog newInstance(Appointment appointment,String[] names){
        ArrangeDialog arrangeDialog = new ArrangeDialog();
        Bundle args = new Bundle();
        args.putSerializable("appointment", appointment);
        args.putSerializable("names",(Serializable)names);
        arrangeDialog.setArguments(args);
        return arrangeDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        if(getArguments()!= null){
            this.names = new String[]{"hello","good","nihao","morning","htydy","gjfyhd"};//(String[])getArguments().getSerializable("names");
            this.appointment = (Appointment)getArguments().getSerializable("appointment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.arrange_dialog,null);
        initPickers(view);
        initButton(view);
        return view;
    }

    private void initButton(View view){
        setLimit =(Button)view.findViewById(R.id.set_limitNum);
        addAppointment = (Button)view.findViewById(R.id.add_appointment);
        cancelAppointment = (Button)view.findViewById(R.id.cancel_appointment);
        setLimit.setOnClickListener(new SetLimitClickListener());
        addAppointment.setOnClickListener(new AddApointmentClickListener());
        cancelAppointment.setOnClickListener(new CancelAppointmentClickListener());
    }

    private void initPickers(View view) {
        limitSelector = (NumberPicker)view.findViewById(R.id.limitSelector);
        limitSelector.setMaxValue(Config.LIMIT_NUM);
        limitSelector.setMinValue(0);
        limitSelector.setValue(appointment.getLimitNum());

        userSelector = (NumberPicker)view.findViewById(R.id.userSelector);
        userSelector.setDisplayedValues(names);
        userSelector.setMaxValue(names.length - 1);
        userSelector.setMinValue(0);
        userSelector.setValue(names.length / 2);

        hour_Selector = (NumberPicker)view.findViewById(R.id.hour_Selector);
        hour_Selector.setFormatter(this);
        hour_Selector.setMaxValue(20);
        hour_Selector.setMinValue(8);
        hour_Selector.setValue(9);

        minute_Selector = (NumberPicker)view.findViewById(R.id.minite_Selector);
        minute_Selector.setFormatter(this);
        minute_Selector.setMaxValue(59);
        minute_Selector.setMinValue(0);
        minute_Selector.setValue(30);
    }

    @Override
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    private String getDateString(){
        StringBuilder result = new StringBuilder();
        result.append(appointment.toString().substring(0, 10) + " ");
        result.append(format(hour_Selector.getValue()) + "-");
        result.append(format(minute_Selector.getValue()));
        return result.toString();
    }

    private class SetLimitClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }

    private class AddApointmentClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }

    private class CancelAppointmentClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }
}
