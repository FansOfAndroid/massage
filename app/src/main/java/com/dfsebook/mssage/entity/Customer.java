package com.dfsebook.mssage.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Customer implements Serializable{
	private int CustomerId ;
	private String CustomerName ;
	private String CustomerPwd ;
	private String CustomerIMSI ;
	private String CustomerTel ;
	private String CustomerEmail ;
	private String CustomerRegTime ;
	private int CustomerLogTime ;
	private String CustomerLastLogT;
	private String CustomerPhoto;
	private int CustomerClass;

	public int getCustomerClass() {
		return CustomerClass;
	}

	public void setCustomerClass(int customerClass) {
		CustomerClass = customerClass;
	}

	public String getCustomerPhoto() {
		return CustomerPhoto;
	}

	public void setCustomerPhoto(String customerPhoto) {
		CustomerPhoto = customerPhoto;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerPwd() {
		return CustomerPwd;
	}

	public void setCustomerPwd(String customerPwd) {
		CustomerPwd = customerPwd;
	}

	public String getCustomerIMSI() {
		return CustomerIMSI;
	}

	public void setCustomerIMSI(String customerIMSI) {
		CustomerIMSI = customerIMSI;
	}

	public String getCustomerTel() {
		return CustomerTel;
	}

	public void setCustomerTel(String customerTel) {
		CustomerTel = customerTel;
	}

	public String getCustomerEmail() {
		return CustomerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}

	public String getCustomerRegTime() {
		return CustomerRegTime;
	}

	public void setCustomerRegTime(String customerRegTime) {
		CustomerRegTime = customerRegTime;
	}

	public int getCustomerLogTime() {
		return CustomerLogTime;
	}

	public void setCustomerLogTime(int customerLogTime) {
		CustomerLogTime = customerLogTime;
	}

	public String getCustomerLastLogT() {
		return CustomerLastLogT;
	}

	public void setCustomerLastLogT(String customerLastLogT) {
		CustomerLastLogT = customerLastLogT;
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

	public Map<String,Object> getProperties(){
		Map<String,Object> result = new HashMap<>();
		result.put("CustomerId",CustomerId);
		result.put("CustomerName", CustomerName);
		result.put("CustomerIMSI",CustomerIMSI);
		result.put("CustomerEmail",CustomerEmail);
		result.put("CustomerPhoto",CustomerPhoto);
		result.put("CustomerClass",CustomerClass);
		result.put("CustomerPwd",CustomerPwd);
		result.put("CustomerTel",CustomerTel);
		result.put("CustomerLastLogT",CustomerLastLogT);
		result.put("CustomerRegTime",CustomerRegTime);
		result.put("CustomerLogTime",CustomerLogTime);
		return result;
	}
}
