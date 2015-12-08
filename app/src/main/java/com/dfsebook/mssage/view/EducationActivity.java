package com.dfsebook.mssage.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.util.String2Bitmap;

public class EducationActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        ImageView imageView = (ImageView)findViewById(R.id.pic);
        imageView.setImageBitmap(String2Bitmap.convert(100,100,"选择图片"));
    }

    private class MyWebviewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }
}
