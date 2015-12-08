package com.dfsebook.mssage.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.DownLoadApk;

public class DownDialog {

	Activity activity;

	AlertDialog.Builder builder ;

	private boolean isReg;

	public DownDialog(Activity activity,boolean isReg){
		this.activity = activity;
		this.isReg = isReg;
		set();
	}

	private void set(){
		StringBuffer sb = new StringBuffer();
		sb.append("骨科推拿中心");
		sb.append("\n");
		sb.append(DataSource.getData().getAppVersion().getAppname());
		sb.append("\n");
		sb.append("版本号:");
		sb.append(DataSource.getData().getAppVersion().getVername());
		builder = new AlertDialog.Builder(activity);
		builder.setTitle("检测到应用程序更新");
		builder.setMessage(sb);
		builder.setPositiveButton("下载", new download());
		builder.setNegativeButton("取消", new mycancel());
		builder.create();
		builder.show();
	}

	private class download implements OnClickListener{
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			Uri uri = Uri.parse(Config.SERVER_APK_PATH);
			intent.setDataAndType(uri,"application/vnd.android.package-archive");
			activity.startActivity(intent);
		}		
	}

	private class mycancel implements OnClickListener{
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			DownLoadApk.skip2LoadOrActivity(activity, isReg);
		}
	}
}
