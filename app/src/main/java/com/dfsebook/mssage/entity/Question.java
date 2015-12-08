package com.dfsebook.mssage.entity;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import com.google.gson.Gson;

public class Question implements Serializable{

	private int questionId ;

	private int customerId ;

	private String customerName ;

	private int questionTitle ;

	private String questionContent ;

	private String questionDate ;

	private String customerPhoto;

	private boolean newQuestion;

	public boolean isNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(boolean newQuestion) {
		this.newQuestion = newQuestion;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(int questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionDate() {
		return questionDate;
	}

	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}

	public String getCustomerPhoto() {
		return customerPhoto;
	}

	public void setCustomerPhoto(String customerPhoto) {
		this.customerPhoto = customerPhoto;
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
