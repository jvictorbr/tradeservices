package com.citi.tradeservices.webservices;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.citi.tradeservices.domain.common.ErrorInfo;
import com.citi.tradeservices.exception.NoDataFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final transient Logger log = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler({ NoDataFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorInfo noDataFoundException(HttpServletRequest request, Exception ex) {
		
		log.error("NoDataFoundException thrown at: " + request.getRequestURI(), ex);
		
		return new ErrorInfo(request.getRequestURI(), 
				"No records could be found", 
				String.format("Provided filters: isError: [%s], from: [%s], to: [%s]", request.getParameter("isError"), request.getParameter("from"), request.getParameter("to")),
				null);		

	}
	
	@ResponseBody
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo internalError(HttpServletRequest request, Exception ex) { 
		
		log.error("Unexpected exception thrown at: " + request.getRequestURI(), ex);
		
		return new ErrorInfo(request.getRequestURI(), "Internal Error", null, ex.getLocalizedMessage());	
		
	}

}
