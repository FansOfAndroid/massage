package com.dfsebook.mssage.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.util.DataSource;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar mToolbar;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mToolbar = (Toolbar) findViewById(R.id.demotoolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.testDrawerLayout);
        navigation = (NavigationView) findViewById(R.id.demonavigation);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, mToolbar, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent();
                switch (menuItem.getOrder()) {
                    case 0:
                        intent.setClass(MainActivity.this, IntroduceActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, ActivesActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, CustomerActivity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, QuestionActivity.class);
                        break;
                    case 4:
                        if(DataSource.currentCustomer != null &&
                                DataSource.currentCustomer.getCustomerClass() == Config.ADMINISTRATORID)
                            intent.setClass(MainActivity.this,ArrangeActivity.class);
                        else
                            intent.setClass(MainActivity.this, AppointmentActivity.class);
                        break;
                    default:
                        intent.setClass(MainActivity.this,EducationActivity.class);
                        break;
                }
                MainActivity.this.startActivity(intent);
                return false;
            }
        });
    }
}
