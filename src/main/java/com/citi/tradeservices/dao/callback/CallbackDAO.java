package com.citi.tradeservices.dao.callback;

import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;

public interface CallbackDAO {
	
	Callback getCallback(CallbackRequest request);

}
