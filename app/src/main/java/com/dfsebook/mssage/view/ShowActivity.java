package com.dfsebook.mssage.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.app.FragmentManager;
import android.os.Bundle;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.asynctask.ImageLoadAsyncTask;
import com.dfsebook.mssage.entity.ActivePicture;
import com.dfsebook.mssage.fragments.ImageDataFragment;
import com.polites.android.GestureImageView;

public class ShowActivity extends Activity {

    private GestureImageView imageView;

    private ImageDataFragment imageDataFragment;

    private ImageLoadAsyncTask imageLoadAsyncTask;

    private Bitmap btm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_show);
         imageView = (GestureImageView)findViewById(R.id.showPic);
        Intent intent = getIntent();
        ActivePicture activePicture = (ActivePicture)intent.getSerializableExtra("picture");

        FragmentManager fm = getFragmentManager();
        imageDataFragment = (ImageDataFragment)fm.findFragmentByTag("data");
        if(imageDataFragment == null){
            imageDataFragment = new ImageDataFragment();
            fm.beginTransaction().add(imageDataFragment,"data").commit();
        }

        imageLoadAsyncTask = imageDataFragment.getData();
        if(imageLoadAsyncTask != null){
            imageLoadAsyncTask.setActivity(this);
        }
        else {
            imageLoadAsyncTask = new ImageLoadAsyncTask(this);
            imageDataFragment.setData(imageLoadAsyncTask);
            imageLoadAsyncTask.execute(Config.SERVICE_BPIC + activePicture.getImageUrl());
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(btm != null && btm.getHeight() <= btm.getWidth())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(btm != null && btm.getHeight() > btm.getWidth())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        imageLoadAsyncTask.setActivity(null);
        super.onSaveInstanceState(outState);
    }

    public void onTaskCompleted(Bitmap bitmap){
        if(bitmap != null) {
            btm = bitmap;
            imageView.setImageBitmap(bitmap);
        }
        else {
            btm = null;
            imageView.setImageResource(R.drawable.ic_launcher);
        }
    }
}
