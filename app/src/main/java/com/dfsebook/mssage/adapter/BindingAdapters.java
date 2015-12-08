package com.dfsebook.mssage.adapter;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.util.MyProgressBar;
import com.dfsebook.mssage.util.OperateImageView;

/**
 * Created by Administrator on 15-10-22.
 */
public class BindingAdapters {

    @BindingAdapter({"bind:imageUrl","bind:showDetail"})
    public static void showImageByUrl(ImageView imageView,String imageUrl,boolean showDetail){
        if (showDetail)
            imageUrl = Config.SERVICE_BPIC + imageUrl ;
        else
            imageUrl = Config.SERVICE_SPIC + imageUrl;
        OperateImageView.loadImageVolley(imageUrl, imageView);
    }
    @BindingAdapter({"bind:photoId"})
    public static void showImageById(ImageView imageView,int photoId){
        imageView.setImageResource(photoId);
    }

    @BindingAdapter({"bind:customerPhoto"})
    public static void showTouXiang(ImageView imageView,String customerPhoto){
        OperateImageView.loadImageVolley(Config.SERVICE_SPIC + customerPhoto, imageView);
    }

    @BindingAdapter({"bind:showInfo"})
    public static void showInfo(TextView textView,int station){
        if(station == 1)
            textView.setText("未设定预约");
        if(station == 2)
            textView.setText("预约已满");
    }

    @BindingAdapter({"bind:year","bind:month"})
    public static void showYM(TextView textView,String year,String month){
        textView.setText(year + "-" + month);
    }

    @BindingAdapter({"bind:wait"})
    public static void showWait(TextView textView,int wait){
        textView.setText("等候预约" + wait);
    }

    @BindingAdapter({"bind:weekend"})
    public static void showWeekend(TextView textView,boolean weekend){
        Resources resources = MyApp.appContext.getResources();
        ColorStateList csl;
        if(weekend)
            csl= (ColorStateList) resources.getColorStateList(R.color.weekend);
        else
            csl = (ColorStateList) resources.getColorStateList(R.color.week);
        textView.setTextColor(csl);
    }

    @BindingAdapter({"bind:checkStation"})
    public static void  checkStation(CheckBox checkBox,boolean appointmented){
        if(appointmented)
            checkBox.setChecked(true);
    }

    @BindingAdapter({"bind:station"})
    public static void  checkTitle(TextView textView,int station){
        if(station == 1)
            textView.setText("预约成功");
        else if(station == 0)
            textView.setText("等待预约");
        else if (station == 2)
            textView.setText("等待撤销");
        else
            textView.setText("等待更改");
    }

    @BindingAdapter({"bind:checkEnable"})
    public static void checkEnable(CheckBox checkBox,int customerClass){
        if(customerClass == 3)
            checkBox.setEnabled(true);
        else
            checkBox.setEnabled(false);
    }

    @BindingAdapter({"bind:dateText"})
    public static void  setDate(TextView textView,String dateString){
        if(dateString.length() > 12)
            textView.setText(dateString.substring(11));
        else
            textView.setText(dateString);
    }

    @BindingAdapter({"bind:limitNum"})
    public static void setLimit(TextView textView,int num){
        textView.setText("预约限额：" + num);
    }

    @BindingAdapter({"bind:remain"})
    public static void setRemain(TextView textView,String remain){
        textView.setText("剩余预约：" + remain);
    }

    @BindingAdapter({"bind:ocupy"})
    public static void setOcupy(TextView textView,int num){
        textView.setText("已预约数：" + num);
    }

    @BindingAdapter({"bind:date","bind:infoType","bind:content"})
    public static void setOperater(TextView textView,String date,int infoType,String content){
        String showString = "";
        if(infoType == Config.OPERATER_WAIT_IN)
            showString = "申请预约：时间 " + date;
        if(infoType == Config.OPERATER_WAIT_DELETE)
            showString = "申请撤销 " + date + " 的预约";
        if(infoType == Config.OPERATER_WAIT_CHANGE)
            showString = "申请更改 " + date + " 的预约到 " + content;
        if(infoType == Config.OPERATER_QUESTION)
            showString = " 提出一个问题: " + content;
        if(infoType == Config.OPERATER_REPLY)
            showString = "回答一个问题: " + content;
        if(infoType == Config.OPERATER_SHARING)
            showString = "分享一条信息: " + content;
        textView.setText(showString);
    }

    @BindingAdapter({"bind:progress"})
    public static void setProgress(MyProgressBar myProgressBar,int progress){
        myProgressBar.setProgress(progress);
    }

    @BindingAdapter({"bind:secondaryProgress"})
    public static void setSecondaryProgress(MyProgressBar myProgressBar,int progress){
        myProgressBar.setSecondaryProgress(progress);
    }

    @BindingAdapter({"bind:max"})
    public static void setMax(MyProgressBar myProgressBar,int max){
        myProgressBar.setMax(max);
    }

    @BindingAdapter({"bind:monthAndDay"})
    public static void setMonthAndDay(TextView textView ,String mad){
        textView.setText(mad + "日");
    }
}
