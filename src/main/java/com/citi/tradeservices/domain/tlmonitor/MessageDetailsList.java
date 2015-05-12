package com.citi.tradeservices.domain.tlmonitor;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageDetailsList implements Serializable {
	
	private static final long serialVersionUID = 148732355702579121L;
	private List<MessageDetails> messageDetails;
	
	public MessageDetailsList() { 
		
	}
	
	public MessageDetailsList(List<MessageDetails> messageDetails) { 
		this.messageDetails = messageDetails;		
	}

	public List<MessageDetails> getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(List<MessageDetails> messageDetails) {
		this.messageDetails = messageDetails;
	}
	
}