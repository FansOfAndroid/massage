package com.dfsebook.mssage.entity;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import com.google.gson.Gson;

public class Refresh implements Serializable{

	private int refreshId ;

	private int customerId ;

	private int questionId ;

	private int replyId ;

	public int getRefreshId() {
		return refreshId;
	}

	public void setRefreshId(int refreshId) {
		this.refreshId = refreshId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
