package com.dfsebook.mssage.entity;


import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import com.google.gson.Gson;

public class AppVersion {	
	private int appId ;

	private String appname ;

	private String apkname ;

	private int vercode ;

	private String vername ;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getApkname() {
		return apkname;
	}

	public void setApkname(String apkname) {
		this.apkname = apkname;
	}

	public int getVercode() {
		return vercode;
	}

	public void setVercode(int vercode) {
		this.vercode = vercode;
	}

	public String getVername() {
		return vername;
	}

	public void setVername(String vername) {
		this.vername = vername;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		String gsonString = gson.toJson(this);
		try {
			gsonString = URLEncoder.encode(gsonString, "UTF-8");
			return gsonString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
