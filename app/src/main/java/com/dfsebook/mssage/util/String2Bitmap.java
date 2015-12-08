package com.dfsebook.mssage.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Base64;

/**
 * Created by Administrator on 15-12-8.
 */
public class String2Bitmap {

    public static Bitmap convert(int width,int height,String text) {
        Bitmap newBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(20.0F);
        StaticLayout sl= new StaticLayout(text, textPaint, newBitmap.getWidth()-8, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
//        canvas.translate(6, 40);
        sl.draw(canvas);
        return newBitmap;
    }
}
