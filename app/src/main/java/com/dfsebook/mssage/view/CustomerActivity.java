package com.dfsebook.mssage.view;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.CustomerAndSharingAdapter;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.fragments.CustomerFragment;
import com.dfsebook.mssage.fragments.SharingFragment;
import com.dfsebook.mssage.slidinglayout.SlidingTabLayout;
import com.dfsebook.mssage.util.DataSource;

import java.util.ArrayList;
import java.util.List;


public class CustomerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private SlidingTabLayout slidingTabLayout;

    private List<Sharing> sharings;

    private CustomerAndSharingAdapter cAS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        sharings = DataSource.getData().getSharings();
        CustomerFragment customerFragment = new CustomerFragment();
        SharingFragment sharingFragment = SharingFragment.newInstance(sharings);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(customerFragment);
        fragments.add(sharingFragment);
        cAS = new CustomerAndSharingAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(cAS);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.CYAN;
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sharings = DataSource.getData().getSharings();
        //更新FragmentPagerAdapter
        CustomerFragment customerFragment = new CustomerFragment();
        SharingFragment sharingFragment = SharingFragment.newInstance(sharings);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(customerFragment);
        fragments.add(sharingFragment);
        cAS.setFragments(fragments);
    }
}
