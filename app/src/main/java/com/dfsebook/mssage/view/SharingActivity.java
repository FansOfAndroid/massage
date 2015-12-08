package com.dfsebook.mssage.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.SharingAdapter;
import com.dfsebook.mssage.dialog.BackContentDialog;
import com.dfsebook.mssage.dialog.LoginDialog;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.entity.DialogProperty;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.operation.InsertSharing;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;
import com.dfsebook.mssage.util.OperateImageView;

import java.util.List;

public class SharingActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    private List<Sharing> sharings;

    private SharingAdapter sharingAdapter;

    private RecyclerView recyclerView;

    private Customer selectedCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        sharings = (List<Sharing>)intent.getSerializableExtra("sharings");
        selectedCustomer = (Customer)intent.getSerializableExtra("customer");
        setNameInvisible();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(selectedCustomer.getCustomerName());
        ImageView imageView = (ImageView)findViewById(R.id.customerbackdrop);
        String picurl = Config.SERVICE_SPIC + selectedCustomer.getCustomerPhoto();
        OperateImageView.loadImageVolley(picurl,imageView);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_sharing);
        InitRecyclerView.initLinearLayoutVERTICAL(this, recyclerView);
        sharingAdapter = new SharingAdapter(sharings);
        recyclerView.setAdapter(sharingAdapter);
    }

    private void setNameInvisible(){
        for(Sharing sharing : sharings){
            sharing.setSelfShow(false);
        }
    }

}
