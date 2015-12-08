package com.dfsebook.mssage.view;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.ArrangeAndOperateAdapter;
import com.dfsebook.mssage.fragments.ArrangeFragment;
import com.dfsebook.mssage.fragments.OperaterFragment;
import com.dfsebook.mssage.operation.BasicOperate;
import com.dfsebook.mssage.slidinglayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class ArrangeActivity extends FragmentActivity implements BasicOperate.RefreshArrangeListener{

    private ViewPager viewPager;

    private SlidingTabLayout slidingTabLayout;

    public ArrangeAndOperateAdapter aAA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange);
        viewPager = (ViewPager) findViewById(R.id.arrage_viewpager);
        ArrangeFragment arrangeFragment = new ArrangeFragment();
        OperaterFragment operaterFragment = new OperaterFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(arrangeFragment);
        fragments.add(operaterFragment);
        aAA = new ArrangeAndOperateAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(aAA);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.arrage_sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer(){
            @Override
            public int getIndicatorColor(int position) {
                return Color.CYAN;
            }
        });
    }

    @Override
    public void refresh(List<Fragment> fragments) {
        aAA.setFragments(fragments);
    }
}
