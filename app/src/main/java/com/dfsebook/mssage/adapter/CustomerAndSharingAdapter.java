package com.dfsebook.mssage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.fragments.CustomerFragment;
import com.dfsebook.mssage.fragments.SharingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-10-11.
 */
public class CustomerAndSharingAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;

    private List<Fragment> fragments;

    private String[] titles = new String[]{"用户列表","分享列表"};

    public CustomerAndSharingAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override

    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public void setFragments(List<Fragment> fragments) {
        if(this.fragments != null){
            FragmentTransaction ft = fm.beginTransaction();
            for(Fragment f:this.fragments){
                ft.remove(f);
            }
            ft.commit();
            ft=null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
