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
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (!(other instanceof CallbackRequest)) return false;
		CallbackRequest otherRequest = (CallbackRequest) other;
		return otherRequest.getCustomerCNPJ().equals(this.getCustomerCNPJ()) 
				&& otherRequest.getAccount().equals(this.getAccount()); 
	}
	
	@Override
	public int hashCode() { 
		int hashCode = this.getCustomerCNPJ().hashCode();
		hashCode = 29 * hashCode + this.getAccount().hashCode();
		return hashCode;
	}

}
