package com.dfsebook.mssage.dialog;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.entity.AppointmentInfo;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.fragments.ArrangeFragment;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

/**
 * Created by Administrator on 15-11-12.
 */
public class AddAppointmentDalog extends DialogFragment  implements NumberPicker.Formatter{

    private NumberPicker customerSelector;
    private NumberPicker hourSelector;
    private NumberPicker minuteSelector;
    private Appointment appointment;
    private List<Customer> customers;

    public static AddAppointmentDalog newInstance(Appointment appointment){
        AddAppointmentDalog addAppointmentDalog = new AddAppointmentDalog();
        Bundle args = new Bundle();
        args.putSerializable("appointment", appointment);
        addAppointmentDalog.setArguments(args);
        return addAppointmentDalog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        if(getArguments() != null){
            this.appointment = (Appointment)getArguments().getSerializable("appointment");
            this.customers = DataSource.getData().getCustomers();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(DialogFragment.STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.add_appointment,null);
        initNumberPickers(view);
        Button sure = (Button)view.findViewById(R.id.add_appointmentInfo);
        Button cancle = (Button)view.findViewById(R.id.cancel_appointmentInfo);
        sure.setOnClickListener(new AddListener());
        cancle.setOnClickListener(new CancelListener());
        return view;
    }

    private class AddListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(isAppointmented()){
                Toast.makeText(getContext(), getContext().getResources().getString(R.string.appointmented), Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            String dateString = format(hourSelector.getValue()) + ":" + format(minuteSelector.getValue());
            dateString = appointment.toString() + " " + dateString;
            intent.putExtra("dateString", dateString);
            getTargetFragment().onActivityResult(ArrangeFragment.ADD_APOINTMENT, Activity.RESULT_OK, intent);
            AddAppointmentDalog.this.dismiss();
        }
    }

    private class CancelListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            AddAppointmentDalog.this.dismiss();
        }
    }
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    private void initNumberPickers(View view){
        customerSelector = (NumberPicker)view.findViewById(R.id.customer_Selector);
        customerSelector.setDisplayedValues(getCustomerName());
        customerSelector.setMinValue(1);
        customerSelector.setMaxValue(customers.size() - 1);
        customerSelector.setValue(customers.size() / 2);

        hourSelector = (NumberPicker)view.findViewById(R.id.hour_Selector);
        hourSelector.setFormatter(this);
        hourSelector.setValue(9);
        hourSelector.setMaxValue(20);
        hourSelector.setMinValue(8);

        minuteSelector = (NumberPicker)view.findViewById(R.id.minute_Selector);
        minuteSelector.setFormatter(this);
        minuteSelector.setMaxValue(59);
        minuteSelector.setMinValue(1);
        minuteSelector.setValue(30);
    }

    private String[] getCustomerName(){
        String[] customerNames = new String[customers.size()];
        for (int index = 0; index < customers.size(); index++){
            customerNames[index] = customers.get(index).getCustomerName();
        }
        return customerNames;
    }

    private boolean isAppointmented(){
        for(AppointmentInfo appinfo : MyApp.msData.getAppointmentInfos()){
            if(appinfo.getCustomerId() == customers.get(customerSelector.getValue()).getCustomerId())
                return true;
        }
        return false;
    }

}
