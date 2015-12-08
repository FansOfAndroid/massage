package com.dfsebook.mssage.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.util.VolleyErrorHelper;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        Intent intent = getIntent();
        String failue = intent.getStringExtra("failue");
        VolleyError error = (VolleyError)intent.getSerializableExtra("error");
        TextView errorTxt = (TextView)findViewById(R.id.errorTxt);
        if(TextUtils.isEmpty(failue))
            errorTxt.setText(VolleyErrorHelper.getMessage(error, this));
        else
            errorTxt.setText(failue);
    }

}
