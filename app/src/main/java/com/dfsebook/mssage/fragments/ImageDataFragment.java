package com.dfsebook.mssage.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.dfsebook.mssage.asynctask.ImageLoadAsyncTask;

/**
 * Created by Administrator on 15-10-30.
 */
public class ImageDataFragment extends Fragment {

    private ImageLoadAsyncTask data;

    public ImageLoadAsyncTask getData() {
        return data;
    }

    public void setData(ImageLoadAsyncTask data) {
        this.data = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
