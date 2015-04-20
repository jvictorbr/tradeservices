package com.citi.tradeservices.service.callback;

import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;

public interface CallbackService {

	Callback getCallback(CallbackRequest request);
	
}
