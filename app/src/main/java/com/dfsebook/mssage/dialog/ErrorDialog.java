package com.dfsebook.mssage.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.dfsebook.mssage.MyApp;

/**
 * Created by Administrator on 15-10-22.
 */
public class ErrorDialog {

    public static void show(Context context){//TODO:传入参数？
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("系统提示")
                .setMessage("网络服务错误，稍后再试")
                .setPositiveButton("确定", null)
                .show();
    }
}
