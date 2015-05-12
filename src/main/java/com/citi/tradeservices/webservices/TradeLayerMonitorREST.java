package com.citi.tradeservices.webservices;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@RequestMapping(value="/tlmonitor/message/{requestId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Message> getMessage(@PathVariable("requestId") String requestId) {
		
		Message message = service.getMessage(requestId);
		message.add(linkTo(methodOn(TradeLayerMonitorREST.class).getMessage(requestId)).withSelfRel());
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
		
	}
	

	@RequestMapping(value = "/tlmonitor/messages", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<MessageList> getMessages(
			@RequestParam(value = "isError", required = false) Boolean isError,
			@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date to) {

		List<Message> messages = service.getMessages(isError, from, to);

		if (messages.isEmpty()) {
			throw new NoDataFoundException();
		}
		
		for (Message message : messages) { 
			message.add(linkTo(methodOn(TradeLayerMonitorREST.class).getMessage(message.getRequestId())).withSelfRel());
			message.add(linkTo(methodOn(TradeLayerMonitorREST.class).getMessageDetails(message.getRequestId())).withRel("messageDetails"));
		}

		MessageList messageList = new MessageList(messages);
		return new ResponseEntity<MessageList>(messageList, HttpStatus.OK);

	}
	
	@RequestMapping(value="/tlmonitor/messageDetails/{reqId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<MessageDetailsList> getMessageDetails(@PathVariable(value="reqId") String requestId) {
		
		List<MessageDetails> messageDetails = service.getMessageDetails(requestId);
		
		if (messageDetails.isEmpty()) { 
			throw new NoDataFoundException();
		}
		
		MessageDetailsList messageDetailsList = new MessageDetailsList(messageDetails);
		return new ResponseEntity<MessageDetailsList>(messageDetailsList, HttpStatus.OK);
		
	}



}
