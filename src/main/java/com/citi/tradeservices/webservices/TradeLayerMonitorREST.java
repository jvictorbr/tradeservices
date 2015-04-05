package com.citi.tradeservices.webservices;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.domain.tlmonitor.MessageDetails;
import com.citi.tradeservices.domain.tlmonitor.MessageDetailsList;
import com.citi.tradeservices.domain.tlmonitor.MessageList;
import com.citi.tradeservices.exception.NoDataFoundException;
import com.citi.tradeservices.service.tlmonitor.MessageService;

@RestController
public class TradeLayerMonitorREST {

	@Autowired
	private MessageService service;


	@RequestMapping(value = "/tlmonitor/messages", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ResponseBody
	public ResponseEntity<MessageList> getMessages(
			@RequestParam(value = "isError", required = false) Boolean isError,
			@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date to) {

		List<Message> messages = service.getMessages(isError, from, to);

		if (messages.isEmpty()) {
			throw new NoDataFoundException();
		}

		MessageList messageList = new MessageList(messages);
		return new ResponseEntity<MessageList>(messageList, HttpStatus.OK);

	}
	
	@RequestMapping(value="/tlmonitor/messageDetails", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ResponseBody
	public ResponseEntity<MessageDetailsList> getMessageDetails(@RequestParam(value="reqId", required=true) String requestId) {
		
		List<MessageDetails> messageDetails = service.getMessageDetails(requestId);
		
		if (messageDetails.isEmpty()) { 
			throw new NoDataFoundException();
		}
		
		MessageDetailsList messageDetailsList = new MessageDetailsList(messageDetails);
		return new ResponseEntity<MessageDetailsList>(messageDetailsList, HttpStatus.OK);
		
	}



}
