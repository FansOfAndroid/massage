package com.dfsebook.mssage.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.dfsebook.mssage.view.ErrorActivity;

import java.io.Serializable;

public class AppErrorListener implements ErrorListener {

	private Context context;

	public AppErrorListener(Context context) {
		this.context = context;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		Intent intent = new Intent(context, ErrorActivity.class);
		intent.putExtra("error",(Serializable)error);
		context.startActivity(intent);
	}

}
