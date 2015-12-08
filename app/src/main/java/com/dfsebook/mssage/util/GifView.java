package com.dfsebook.mssage.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import com.dfsebook.mssage.R;

import java.io.InputStream;

/**
 * Created by Administrator on 15-10-10.
 */
public class GifView extends View {

    Movie movie;
    long mStart;

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InputStream is = getContext().getResources().openRawResource(R.drawable.loading);
        movie = Movie.decodeStream(is);
    }

    public GifView(Context context){
        this(context, null);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        int duration = movie.duration();
        long now = android.os.SystemClock.uptimeMillis();
        if(mStart == 0){
            mStart = now;
        }
        movie.setTime((int) ((now - mStart) % duration));
        movie.draw(canvas,0, 0);
        invalidate();
    }
}
