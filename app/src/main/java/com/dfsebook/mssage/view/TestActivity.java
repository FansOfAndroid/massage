package com.dfsebook.mssage.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.util.String2Bitmap;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView imageView = (ImageView)findViewById(R.id.testpic);
        imageView.setImageBitmap(String2Bitmap.convert(100,100,"选择图片"));

    }

}
