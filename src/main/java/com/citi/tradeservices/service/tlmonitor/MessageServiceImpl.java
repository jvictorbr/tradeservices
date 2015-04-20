package com.citi.tradeservices.service.tlmonitor;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.tradeservices.dao.tlmonitor.MessageDAO;
import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.domain.tlmonitor.MessageDetails;

@Service
@Transactional(readOnly=true)
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDAO dao;
	
	
	public List<Message> getMessages(Boolean isError, Date from, Date to) {		
		return dao.getMessages(isError, from, to);	
	}


	@Override
	public List<MessageDetails> getMessageDetails(String requestId) {
		return dao.getMessageDetails(requestId);
	}

}
