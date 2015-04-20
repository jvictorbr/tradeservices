package com.citi.tradeservices.service.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.tradeservices.dao.callback.CallbackDAO;
import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;

@Service
@Transactional(readOnly=true)
public class CallbackServiceImpl implements CallbackService {

	@Autowired
	private CallbackDAO dao;

	@Override
	public Callback getCallback(CallbackRequest request) {
	
		return dao.getCallback(request);
		
	}
	

	
}
