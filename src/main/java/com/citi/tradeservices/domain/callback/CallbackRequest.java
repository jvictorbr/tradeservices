package com.citi.tradeservices.domain.callback;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallbackRequest implements Serializable {
	
	private static final long serialVersionUID = 2732813660442001863L;
	
	private final String id;
	private String customerCNPJ;
	private String account;
	
	public CallbackRequest() { 
		this.id = generateId();
	}
	
	public CallbackRequest(final String customerCNPJ, final String account) { 
		this.id = generateId();
		this.customerCNPJ = customerCNPJ;
		this.account = account;
	}
	
	public String getId() {
		return id;
	}
	public String getCustomerCNPJ() {
		return customerCNPJ;
	}
	public void setCustomerCNPJ(String customerCNPJ) {
		this.customerCNPJ = customerCNPJ;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	private String generateId() {
		
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmssSSS");		
		String id = "ManCallback_" + df.format(new Date());
		
		return id;
		
	}

}
