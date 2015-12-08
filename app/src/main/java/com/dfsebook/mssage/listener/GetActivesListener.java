package com.dfsebook.mssage.listener;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.Response;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.ActivePicture;
import com.dfsebook.mssage.entity.ActivePictureList;
import com.dfsebook.mssage.view.ActivesActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-10-9.
 */
public class GetActivesListener implements Response.Listener<String> {

    private Activity activity;

    public GetActivesListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(String s) {
        if(s.equals(Config.OPERATE_FAIL))
            ErrorDialog.show(activity);
        else {
            Gson gson = new Gson();
            Type stringType = new TypeToken<List<String>>() {
            }.getType();
            List<String> pictureNames = gson.fromJson(s, stringType);
            List<ActivePicture> activePictures = new ArrayList<>();
            for (String name : pictureNames) {
                ActivePicture activePicture = new ActivePicture();
                activePicture.setImageUrl(name);
                activePicture.setShowDetail(false);
                activePictures.add(activePicture);
            }
            Intent intent = new Intent(activity, ActivesActivity.class);
            intent.putExtra("aps", (Serializable) activePictures);
            activity.startActivity(intent);
        }
    }
}
