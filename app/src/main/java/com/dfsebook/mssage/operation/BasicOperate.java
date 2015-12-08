package com.dfsebook.mssage.operation;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.dfsebook.mssage.fragments.ArrangeFragment;
import com.dfsebook.mssage.fragments.OperaterFragment;
import com.dfsebook.mssage.util.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-20.
 */
public class BasicOperate {

    public interface RefreshArrangeListener {
        public void refresh(List<Fragment> fragments);
    }

    protected void OperateDataSource(){
        DataSource.writeFileToSDCard();
    }

    public void OperateWebService(){}

    protected void NotifyDataSetChange(){}

    protected void NotifyOperaterChange(Context context){
        ArrangeFragment arrangeFragment = new ArrangeFragment();
        OperaterFragment operaterFragment = new OperaterFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(arrangeFragment);
        fragments.add(operaterFragment);
        RefreshArrangeListener refreshArrangeListener = (RefreshArrangeListener)context;
        refreshArrangeListener.refresh(fragments);
    }

}
