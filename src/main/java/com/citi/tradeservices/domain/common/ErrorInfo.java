package com.citi.tradeservices.domain.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorInfo {

	private  String url;
	private  String error;
	private  String detailMessage;
	private  String exceptionMessage;
	
	public ErrorInfo(String url, String error, String detailMessage, String exceptionMessage) { 
		this.url = url;
		this.error = error;
		this.detailMessage = detailMessage;
		this.exceptionMessage = exceptionMessage;
	}
	
	public ErrorInfo() {
		
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public String getUrl() {
		return url;
	}

	public String getError() {
		return error;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}	
	
}
