package com.citi.tradeservices.service.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.tradeservices.dao.callback.CallbackDAO;
import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;
import com.citi.tradeservices.exception.NoDataFoundException;

@Service
@Transactional(readOnly=true)
public class CallbackServiceImpl implements CallbackService {

	@Autowired
	private CallbackDAO dao;

	@Override
	public Callback getCallback(CallbackRequest request) {
	
		Callback callback = dao.getCallback(request);
		if (callback == null || callback.getOrderCount() == 0) { 
			throw new NoDataFoundException();
		}
		
		return callback;
		
	}
	

	
}
