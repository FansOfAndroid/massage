package com.dfsebook.mssage.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.dfsebook.mssage.dialog.LoadDialog;
import com.dfsebook.mssage.view.ShowActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 15-10-30.
 */
public class ImageLoadAsyncTask extends AsyncTask <String,Void,Bitmap>{

    private ShowActivity activity;

    private Bitmap bitmap;

    private boolean isCompleted;

    private LoadDialog loadDialog;

    public ImageLoadAsyncTask(ShowActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        loadDialog = new LoadDialog();
        loadDialog.show(activity.getFragmentManager(),"Loading");
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        isCompleted = true;
        this.bitmap = bitmap;
        notifyActivityTaskCompleted();
        if(loadDialog != null)
            loadDialog.dismiss();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL imgUrl = null;
        Bitmap btm = null;
        try {
            imgUrl = new URL(strings[0]);
            HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
            urlConn.setDoInput(true);
            urlConn.connect();
            InputStream is = urlConn.getInputStream();
            btm = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return btm;
    }

    /**
     * 设置Activity，因为Activity会一直变化
     *
     * @param activity
     */
    public void setActivity(ShowActivity activity)
    {
        // 如果上一个Activity销毁，将与上一个Activity绑定的DialogFragment销毁
        if (activity == null){
            loadDialog.dismiss();
        }
        // 设置为当前的Activity
        this.activity = activity;
        // 开启一个与当前Activity绑定的等待框
        if (activity != null && !isCompleted)
        {
            loadDialog = new LoadDialog();
            loadDialog.show(activity.getFragmentManager(), "LOADING");
        }
        // 如果完成，通知Activity
        if (isCompleted) {
            notifyActivityTaskCompleted();
        }
    }

    private void notifyActivityTaskCompleted()
    {
        if (null != activity)
        {
            activity.onTaskCompleted(bitmap);
        }
    }

}
