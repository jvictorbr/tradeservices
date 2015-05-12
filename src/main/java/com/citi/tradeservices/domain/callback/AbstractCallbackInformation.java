package com.citi.tradeservices.domain.callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCallbackInformation implements Serializable {

	private static final long serialVersionUID = 1298033874930946709L;
	
	private String bicAccount;
	private String bicCode;
	private String ccClear;
	private String bankName;
	private List<String> addresses = new ArrayList<String>();
		
	public String getBicAccount() {
		return bicAccount;
	}
	public void setBicAccount(String bicAccount) {
		this.bicAccount = bicAccount;
	}
	public String getBicCode() {
		return bicCode;
	}
	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}
	public String getCcClear() {
		return ccClear;
	}
	public void setCcClear(String ccClear) {
		this.ccClear = ccClear;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<String> getAddresses() {
		return addresses;
	}
	public void addAddress(String address) { 
		this.addresses.add(address);
	}
	
	public static class IntermediaryInstitution extends AbstractCallbackInformation {
		
	}
	
	public static class AccountInstitution extends AbstractCallbackInformation { 
		
	}
	
	public static class BeneficiaryCustomer extends AbstractCallbackInformation { 
		
	}
	
}
