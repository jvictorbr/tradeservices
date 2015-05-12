package com.citi.tradeservices.dao.tlmonitor;

import java.util.Date;
import java.util.List;

import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.domain.tlmonitor.MessageDetails;


public interface MessageDAO {
	
	Message getMessage(String messageId);
	List<Message> getMessages(Boolean isError, Date from, Date to);
	List<MessageDetails> getMessageDetails(String requestId);

}
