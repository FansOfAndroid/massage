package com.dfsebook.mssage.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dfsebook.mssage.R;

/**
 * Created by Administrator on 15-11-23.
 */
public class CustomerAlertDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.login_pop,null);
        TextView textView = (TextView)view.findViewById(R.id.show_something);
        textView.setText("该日预约已满");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setPositiveButton(getContext().getResources().getString(R.string.sureClick),null);
        return builder.create();
    }
}
