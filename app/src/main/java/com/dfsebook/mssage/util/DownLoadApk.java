package com.dfsebook.mssage.util;

import android.app.Activity;
import android.content.Intent;

import com.dfsebook.mssage.view.LoginActivity;
import com.dfsebook.mssage.view.MainActivity;

/**
 * Created by Administrator on 15-9-24.
 */
public class DownLoadApk {
    public static void skip2LoadOrActivity(Activity activity,boolean isReg){
        if(isReg){
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
        else {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
