package com.dfsebook.mssage.entity;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import com.google.gson.Gson;

public class Reply implements Serializable{

	private int replyId ;

	private int questionId ;

	private int customerId ;

	private int replyTitle ;

	private String replyContent ;

	private String customerName ;

	private String commentDate ;

	private String customerPhoto;

	public String getCustomerPhoto() {
		return customerPhoto;
	}

	public void setCustomerPhoto(String customerPhoto) {
		this.customerPhoto = customerPhoto;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getReplyTitle() {
		return replyTitle;
	}

	public void setReplyTitle(int replyTitle) {
		this.replyTitle = replyTitle;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
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
