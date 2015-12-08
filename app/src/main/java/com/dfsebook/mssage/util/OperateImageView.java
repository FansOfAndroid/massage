package com.dfsebook.mssage.util;

import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.polites.android.GestureImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 15-10-9.
 */
public class OperateImageView {

    public static void loadImageVolley(String url,ImageView imageView){
        final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache(){
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key, value);
            }

        };
        ImageLoader imageLoader = new ImageLoader(MyApp.mRequestQueue,imageCache);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
        imageLoader.get(url, listener);
    }

    public static void setGestureImageView(final String urlString,
                                           final GestureImageView gestureImageView,
                                           final ProgressDialog progressDialog) {

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                Bitmap bitmap = (Bitmap)msg.obj;
                progressDialog.dismiss();
                gestureImageView.setImageBitmap(bitmap);

            }
        };
        new Thread(){
            public void run(){
                URL imgUrl = null;
                Bitmap bitmap = null;
                try {
                    imgUrl = new URL(urlString);
                    HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.connect();
                    InputStream is = urlConn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = handler.obtainMessage(1,bitmap);
                handler.sendMessage(message);
            }
        }.start();
    }
}
