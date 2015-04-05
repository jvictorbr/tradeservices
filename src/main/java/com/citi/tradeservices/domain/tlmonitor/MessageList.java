package com.citi.tradeservices.domain.tlmonitor;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageList implements Serializable {
	
	private static final long serialVersionUID = -4075536695567646386L;
	private List<Message> messages;
	
	public MessageList() { 
		
	}
	
	public MessageList(List<Message> messages) { 
		this.messages = messages;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	

}
