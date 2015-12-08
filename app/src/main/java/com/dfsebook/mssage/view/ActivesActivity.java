package com.dfsebook.mssage.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.PictureAdapter;
import com.dfsebook.mssage.entity.ActivePicture;
import com.dfsebook.mssage.entity.ActivePictureList;
import com.dfsebook.mssage.util.InitRecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ActivesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<ActivePicture> activePictures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView = (RecyclerView)findViewById(R.id.showList);
        InitRecyclerView.initStaggered(this, recyclerView);
        activePictures = new ArrayList<>();
        getActivePictures();
        PictureAdapter pictureAdapter = new PictureAdapter(activePictures);
        recyclerView.setAdapter(pictureAdapter);
        pictureAdapter.setOnItemClickLitener(new PictureAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ActivePicture activePicture = activePictures.get(position);
                Intent intent1 = new Intent(ActivesActivity.this,ShowActivity.class);
                intent1.putExtra("picture",activePicture);
                ActivesActivity.this.startActivity(intent1);
            }
        });
    }

    private void getActivePictures(){
        for(String s : MyApp.msData.getPictures()){
            ActivePicture activePicture = new ActivePicture();
            activePicture.setImageUrl(s);
            activePictures.add(activePicture);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //设置菜单的背景色和字体颜色
/*        getLayoutInflater().setFactory(new LayoutInflater.Factory() {
            @Override
            public View onCreateView(String name, Context context, AttributeSet attributeSet) {
                if (name .equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")) {
                    try {
                        LayoutInflater f = getLayoutInflater();
                        final View view = f.createView(name, null, attributeSet);

                        boolean post = new Handler().post(new Runnable() {
                            public void run() {

// set the background drawable
                                view.setBackgroundResource(R.drawable.dialog_bg);

// set the text color
                                ((TextView) view).setTextColor(Color.WHITE);
                            }
                        });
                        return view;
                    } catch (InflateException e) {
                    } catch (ClassNotFoundException e) {
                    }
                }
                return null;


            }
        });*/

        return super.onCreateOptionsMenu(menu);
    }
}
