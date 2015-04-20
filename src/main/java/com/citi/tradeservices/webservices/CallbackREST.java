package com.citi.tradeservices.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;
import com.citi.tradeservices.service.callback.CallbackService;

@RestController
public class CallbackREST {
	
	@Autowired
	private CallbackService callbackService;
	
	@RequestMapping(value="/callback", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Callback> getCallback(@RequestParam(value="cnpj", required=true) String cnpj, @RequestParam(value="account", required=true) String account ) {
		
		CallbackRequest request = new CallbackRequest(cnpj, account);
		Callback callback = callbackService.getCallback(request);
		
		return new ResponseEntity<Callback>(callback, HttpStatus.OK);
		
	}

}
