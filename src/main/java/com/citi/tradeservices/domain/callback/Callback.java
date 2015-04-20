package com.citi.tradeservices.domain.callback;

import java.util.Date;

import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.AccountInstitution;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.BeneficiaryCustomer;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.IntermediaryInstitution;

public class Callback {
	
	private String id;
	private String customerId;
	private String account;
	
	private IntermediaryInstitution intermediaryInstitution;
	private AccountInstitution accountInstitution;
	private BeneficiaryCustomer beneficiaryCustomer;
	
	private Date lastOrderDate;
	private Integer orderCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public IntermediaryInstitution addNewIntermediaryInstitution() { 
		IntermediaryInstitution ii = new IntermediaryInstitution();
		this.intermediaryInstitution = ii;
		return ii;
	}
	public IntermediaryInstitution getIntermediaryInstitution() {
		return intermediaryInstitution;
	}
	public AccountInstitution getAccountInstitution() {
		return accountInstitution;
	}
	public AccountInstitution addNewAccountInstitution() { 
		AccountInstitution ai = new AccountInstitution();
		this.accountInstitution = ai;
		return ai;
	}
	public BeneficiaryCustomer getBeneficiaryCustomer() {
		return beneficiaryCustomer;
	}
	public BeneficiaryCustomer addNewBeneficiaryCustomer() { 
		BeneficiaryCustomer bc = new BeneficiaryCustomer();
		this.beneficiaryCustomer = bc;
		return bc;
	}
	public Date getLastOrderDate() {
		return lastOrderDate;
	}
	public void setLastOrderDate(Date lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

}
