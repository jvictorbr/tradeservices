package com.citi.tradservices.service.callback;

import java.util.Date;

import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.AccountInstitution;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.BeneficiaryCustomer;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.IntermediaryInstitution;



public class CallbackMockBuilder {
	
	public static Callback getEmptyCallback(CallbackRequest request) { 
		
		Callback callback = new Callback();
		callback.setId(request.getId());
		callback.setCustomerId(request.getCustomerCNPJ());
		callback.setAccount(request.getAccount());
		callback.setOrderCount(0);
		
		return callback;
		
	}
	
	public static Callback getMockCallback(CallbackRequest request) {
		
		Callback callback = new Callback();
		callback.setId(request.getId());
		callback.setCustomerId(request.getCustomerCNPJ());
		callback.setAccount(request.getAccount());
		
		AccountInstitution ai = callback.addNewAccountInstitution();
		ai.setBankName("BANK1");
		ai.setBicAccount("BICACCOUNT1");
		ai.setBicCode("BICCODE1");
		ai.setCcClear("CCLEAR1");
		ai.addAddress("AI_ADDRESS1");
		ai.addAddress("AI_ADDRESS2");
		ai.addAddress("AI_ADDRESS3");
		
		BeneficiaryCustomer bc = callback.addNewBeneficiaryCustomer();
		bc.setBankName("BANK2");
		bc.setBicAccount("BICACCOUNT2");
		bc.setBicCode("BICCODE2");
		bc.setCcClear("CCLEAR2");
		bc.addAddress("BC_ADDRESS1");
		bc.addAddress("BC_ADDRESS2");
		bc.addAddress("BC_ADDRESS3");
		
		IntermediaryInstitution ii = callback.addNewIntermediaryInstitution();
		ii.setBankName("BANK3");
		ii.setBicAccount("BICACCOUNT3");
		ii.setBicCode("BICCODE3");
		ii.setCcClear("CCLEAR3");
		ii.addAddress("II_ADDRESS1");
		ii.addAddress("II_ADDRESS2");
		ii.addAddress("II_ADDRESS3");
		
		callback.setOrderCount(10);
		callback.setLastOrderDate(new Date());
		
		return callback;
		
		
	}	

}