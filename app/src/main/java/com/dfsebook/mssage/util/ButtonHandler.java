package com.dfsebook.mssage.util;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 15-11-10.
 */
public class ButtonHandler extends Handler {

    private WeakReference < DialogInterface > mDialog;

    public ButtonHandler(DialogInterface dialog)
    {
        mDialog = new WeakReference< DialogInterface >(dialog);
    }

    @Override
    public void handleMessage(Message msg)
    {
        switch (msg.what)
        {

            case DialogInterface.BUTTON_POSITIVE:
            case DialogInterface.BUTTON_NEGATIVE:
            case DialogInterface.BUTTON_NEUTRAL:
                ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog
                        .get(), msg.what);
                break ;
        }
    }

}
