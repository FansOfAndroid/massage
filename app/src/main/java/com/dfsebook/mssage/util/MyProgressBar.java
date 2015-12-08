package com.dfsebook.mssage.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.dfsebook.mssage.Config;

/**
 * Created by Administrator on 15-11-12.
 */
public class MyProgressBar extends ProgressBar {

    private String text_progress;

    private Paint mPaint;

    public MyProgressBar(Context context) {
        super(context);
        initPaint();
    }
    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public MyProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        setTextProgress(progress);
    }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text_progress, 0, this.text_progress.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        int m = 0;
        int n = 0;
        if(getMax() == 0){
            return;
        }
        if(getMax() == getProgress()){
            canvas.drawText("预约已满", x, y, this.mPaint);
            return;
        }
        if(getProgress() == 0){
            if(getSecondaryProgress() == 0) {
                canvas.drawText(String.valueOf(getMax()), x, y, this.mPaint);
            } else {
                if(getSecondaryProgress() >= getMax()) {
                    canvas.drawText(String.valueOf(getSecondaryProgress()), x, y, this.mPaint);
                } else {
                    double tem = (getMax() + getSecondaryProgress()) * 100 / getMax();
                    tem = getWidth() * tem / 200;
                    m = (int)tem;
                    tem = getSecondaryProgress() * 100 / getMax();
                    tem = getWidth() * tem / 200;
                    n = (int)tem;
                    canvas.drawText(String.valueOf(getSecondaryProgress()),n,y,mPaint);
                    canvas.drawText(String.valueOf(getMax()), m, y, this.mPaint);
                }
            }
        } else {
            double tem = getProgress() * 100 / getMax();
            tem = getWidth() * tem / 200;
            x = (int) tem;
            tem = (getMax() + getSecondaryProgress()) * 100 / getMax();
            tem = getWidth() * tem / 200;
            m = (int) tem;
            tem = (getProgress() + getSecondaryProgress()) * 100 / getMax();
            tem = getWidth() * tem / 200;
            n = (int) tem;
            canvas.drawText(String.valueOf(getProgress()), x, y, this.mPaint);
            if(getSecondaryProgress() > getProgress())
                canvas.drawText(String.valueOf(getSecondaryProgress() - getProgress()), n, y, this.mPaint);
            if(getSecondaryProgress() < getMax())
                canvas.drawText(String.valueOf(getMax() - getProgress()), m, y, this.mPaint);
        }

    }

    private void initPaint(){
        this.mPaint=new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(Config.PROGRESSBAR_TEXTCOLOR);
        this.mPaint.setTextSize(Config.PROGRESSBAR_TEXTSIZE);
    }
    private void setTextProgress(int progress){
        this.text_progress = String.valueOf(progress);
    }
}
