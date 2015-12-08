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

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.fragments.OperaterFragment;

/**
 * Created by Administrator on 15-10-22.
 */
public class SignDialog extends DialogFragment implements NumberPicker.Formatter{

    private NumberPicker hourPicker;

    private NumberPicker minutePicker;

    private Button sureBtn ;

    private Button cancelBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(DialogFragment.STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.sign_dialog, null);
        hourPicker=(NumberPicker)view.findViewById(R.id.hourpicker);
        minutePicker=(NumberPicker)view.findViewById(R.id.minuteicker);
        sureBtn = (Button)view.findViewById(R.id.suer_sign);
        cancelBtn = (Button)view.findViewById(R.id.cancel_sign);
        sureBtn.setOnClickListener(SuerClick);
        cancelBtn.setOnClickListener(CancelClick);
        init();
        return view;
    }

    private void init() {
        hourPicker.setFormatter(this);
        hourPicker.setMaxValue(24);
        hourPicker.setMinValue(0);
        hourPicker.setValue(8);

        minutePicker.setFormatter(this);
        minutePicker.setMaxValue(60);
        minutePicker.setMinValue(0);
        minutePicker.setValue(48);
    }

    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    private View.OnClickListener SuerClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String time = " " + format(hourPicker.getValue()) + ":" + format(minutePicker.getValue());
            Intent intent =  new Intent();
            intent.putExtra("time",time);
            getTargetFragment().onActivityResult(OperaterFragment.REQUEST_SIGN, Activity.RESULT_OK,intent);
            SignDialog.this.dismiss();
        }
    };

    private View.OnClickListener CancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SignDialog.this.dismiss();
        }
    };
}
