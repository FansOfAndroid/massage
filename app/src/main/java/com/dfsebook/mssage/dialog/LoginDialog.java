package com.dfsebook.mssage.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.view.LoginActivity;

/**
 * Created by Administrator on 15-11-20.
 */
public class LoginDialog {

    private Activity activity;

    AlertDialog.Builder builder ;

    public LoginDialog(final Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.login_pop,null);
        this.activity = activity;
        builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        builder.setPositiveButton(activity.getResources().getString(R.string.sure_register),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataSource.reActivity = activity;
                        Intent intent = new Intent(activity, LoginActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                });
        builder.setNegativeButton(activity.getResources().getString(R.string.cancel), null);
        builder.create();
        builder.show();
    }
}
