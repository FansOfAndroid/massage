package com.dfsebook.mssage.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-10-19.
 */
public class ChangeAppointmentDialog extends DialogFragment implements NumberPicker.Formatter{

    private Appointment appointment;
    private Context context;
    private Button appSure;
    private Button appCancel;
    private NumberPicker monthPicker;
    private NumberPicker dayPicker;
    private NumberPicker hourPicker;
    private NumberPicker minutePiker;
    private String tagDate;


    public static ChangeAppointmentDialog newInstance(Appointment appointment){
        ChangeAppointmentDialog changeAppointmentDialog = new ChangeAppointmentDialog();
        Bundle args = new Bundle();
        args.putSerializable("appointment", appointment);
        changeAppointmentDialog.setArguments(args);
        return changeAppointmentDialog;
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
        View view = inflater.inflate(R.layout.appointment_dialog,null);
        initNumberPicker(view);
        appSure = (Button)view.findViewById(R.id.appSure);
        appCancel = (Button)view.findViewById(R.id.appCancel);
        appSure.setOnClickListener(new SureClick());
        appCancel.setOnClickListener(new CancelClick());
        return view;
    }

    private void initNumberPicker(View view){
        monthPicker = (NumberPicker)view.findViewById(R.id.monthSelector);
        monthPicker.setFormatter(this);
        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);
        monthPicker.setValue(getMonth());

        dayPicker = (NumberPicker)view.findViewById(R.id.daySelector);
        dayPicker.setFormatter(this);
        dayPicker.setMaxValue(31);
        dayPicker.setMinValue(1);
        dayPicker.setValue(getDay());

        hourPicker =  (NumberPicker)view.findViewById(R.id.ahourSelector);
        hourPicker.setFormatter(this);
        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(1);
        hourPicker.setValue(8);

        minutePiker = (NumberPicker)view.findViewById(R.id.aminuteSelector);
        minutePiker.setFormatter(this);
        minutePiker.setMaxValue(59);
        minutePiker.setMinValue(1);
        minutePiker.setValue(30);
    }

    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    private int getMonth() {
        String date = appointment.toString();
        date = date.substring(5, 7);
        return Integer.valueOf(date);
    }

    private int getDay(){
        String date = appointment.toString();
        date = date.substring(8,10);
        return Integer.valueOf(date);
    }

    public interface AppointmentDialogListener {
        public void back(String dateString);
    }

    private class SureClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            tagDate = appointment.getYear() + "-" + format(monthPicker.getValue()) + "-" + format(dayPicker.getValue());
            if(testFull(tagDate)){
                Toast.makeText(getContext(), "指定日期预约已满", Toast.LENGTH_SHORT).show();
                return;
            }
            String dateString = appointment.getYear() + "-";
            dateString += format(monthPicker.getValue()) + "-";
            dateString += format(dayPicker.getValue()) + " ";
            dateString += format(hourPicker.getValue()) + ":";
            dateString += format(minutePiker.getValue());
            AppointmentDialogListener listener = (AppointmentDialogListener)getActivity();
            listener.back(dateString);
            ChangeAppointmentDialog.this.dismiss();
        }
    }

    private class CancelClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ChangeAppointmentDialog.this.dismiss();
        }
    }

    private boolean testFull(String date){
        List<Appointment> appointments = DataSource.getAppointments();
        for (Appointment app : appointments){
            if(app.toString().substring(0,10).equals(date)){
                if(app.getLimitNum() == app.getOcupied())
                    return true;
            }
        }
        return false;
    }
}
