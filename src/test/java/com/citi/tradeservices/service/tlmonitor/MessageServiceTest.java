package com.citi.tradeservices.service.tlmonitor;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.domain.tlmonitor.MessageDetails;
import com.citi.tradeservices.exception.NoDataFoundException;
import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@ActiveProfiles(value="Test")
public class MessageServiceTest {
	
	@Autowired
	private MessageService service;
	
	@Test
	public void testGetAllMessages() {
		
		List<Message> allMessages = service.getMessages(null, null, null);
		
		assertThat(allMessages, is(notNullValue()));
		assertThat(allMessages.size(), is(equalTo(2)));	
	
	}
	
	@Test
	public void testGetSuccessMessage() { 
		
		List<Message> successMessages = service.getMessages(Boolean.FALSE, null, null);
		
		assertThat(successMessages, is(notNullValue()));
		assertThat(successMessages.size(), is(equalTo(1)));
		
		Message successMessage = successMessages.get(0);
		
		assertThat(successMessage, is(notNullValue()));
		assertThat(successMessage.getRequestId(), is(equalTo("DUMMY_0101")));
		assertThat(successMessage.getIsError(), is(equalTo(false)));
				
	}
	
	@Test
	public void testGetErrorMessage() {
		
		List<Message> errorMessages = service.getMessages(Boolean.TRUE, null, null);
		
		assertThat(errorMessages, is(notNullValue()));
		assertThat(errorMessages.size(), is(equalTo(1)));
		
		Message errorMessage = errorMessages.get(0);
		
		assertThat(errorMessage, is(notNullValue()));
		assertThat(errorMessage.getRequestId(), is(equalTo("DUMMY_0202")));
		assertThat(errorMessage.getIsError(), is(equalTo(true)));
		
	}
	
	@Test
	public void testGetNoMessage() {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		List<Message> messages = service.getMessages(null, c.getTime(), c.getTime());
		
		assertThat(messages, is(notNullValue()));
		assertThat(messages.size(), is(equalTo(0)));
				
	}
	
	@Test
	public void testGetMessageDetailsSuccess() {

		
		List<MessageDetails> messageDetails = service.getMessageDetails("DUMMY_0101");
		assertThat(messageDetails, is(notNullValue()));
		assertThat(messageDetails.isEmpty(), is(equalTo(false)));
		assertThat(messageDetails.size(), is(equalTo(2)));
		
		MessageDetails requestMessage01 = messageDetails.get(0);
		assertThat(requestMessage01, notNullValue());
		assertThat(requestMessage01.getRequestId(), allOf(notNullValue(), is(equalTo("DUMMY_0101"))));
		assertThat(requestMessage01.getMessageType(), allOf(notNullValue(), is(equalTo("update-payment-order"))));
		assertThat(requestMessage01.getMessageDate(), notNullValue());
		assertThat(requestMessage01.getMessageContent().contains("<msg_pk>DUMMY_0101</msg_pk>"), is(true));
		
		MessageDetails responseMessage01 = messageDetails.get(1);
		assertThat(responseMessage01, notNullValue());
		assertThat(responseMessage01.getRequestId(), allOf(notNullValue(), equalTo("msg_response_DUMMY_0101")));		
		assertThat(responseMessage01.getMessageType(), allOf(notNullValue(), equalTo("update-payment-order-response")));
		assertThat(responseMessage01.getMessageDate(), notNullValue());
		assertThat(responseMessage01.getMessageContent().contains("<msg_pk>msg_response_DUMMY_0101</msg_pk>"), is(true));
		assertThat(responseMessage01.getMessageContent().contains("<reference_msg_pk>DUMMY_0101</reference_msg_pk>"), is(true));
		assertThat(responseMessage01.getMessageContent().contains("<error>"), is(false));
		
	}
	
	@Test
	public void testGetMessageDetailsError() { 
		
		List<MessageDetails> messageDetails = service.getMessageDetails("DUMMY_0202");
		assertThat(messageDetails, notNullValue());
		assertThat(messageDetails.isEmpty(), is(false));
		assertThat(messageDetails.size(), is((equalTo(2))));
		
		MessageDetails requestMessage02 = messageDetails.get(0);
		assertThat(requestMessage02, notNullValue());
		assertThat(requestMessage02.getRequestId(), allOf(notNullValue(), is(equalTo("DUMMY_0202"))));
		assertThat(requestMessage02.getMessageType(), allOf(notNullValue(), is(equalTo("pay-contract"))));
		assertThat(requestMessage02.getMessageDate(), notNullValue());
		assertThat(requestMessage02.getMessageContent().contains("<msg_pk>DUMMY_0202</msg_pk>"), is(true));
		
		MessageDetails responseMessage02 = messageDetails.get(1);
		assertThat(responseMessage02, notNullValue());
		assertThat(responseMessage02.getRequestId(), allOf(notNullValue(), equalTo("msg_response_DUMMY_0202")));		
		assertThat(responseMessage02.getMessageType(), allOf(notNullValue(), equalTo("pay-contract-response")));
		assertThat(responseMessage02.getMessageDate(), notNullValue());
		assertThat(responseMessage02.getMessageContent().contains("<msg_pk>msg_response_DUMMY_0202</msg_pk>"), is(true));
		assertThat(responseMessage02.getMessageContent().contains("<reference_msg_pk>DUMMY_0202</reference_msg_pk>"), is(true));
		assertThat(responseMessage02.getMessageContent().contains("<error>"), is(true));
			
	}
	
	@Test
	public void testGetNoMessageDetails() {
		
		List<MessageDetails> emptyMessageDetails = service.getMessageDetails("NotExistantId");
		
		assertThat(emptyMessageDetails, is(notNullValue()));
		assertThat(emptyMessageDetails.isEmpty(), is(equalTo(true)));
		assertThat(emptyMessageDetails.size(), is(equalTo(0)));
		
	}
	
	@Test
	public void testGetMessage() { 
		
		Message dummy0101 = service.getMessage("DUMMY_0101");
		assertThat(dummy0101, notNullValue());
		assertThat(dummy0101.getRequestId(), is(equalTo("DUMMY_0101")));
		
		Message dummy0202 = service.getMessage("DUMMY_0202");
		assertThat(dummy0202, notNullValue());
		assertThat(dummy0202.getRequestId(), is(equalTo("DUMMY_0202")));
		
	}
	
	@Test(expected=NoDataFoundException.class)
	public void testGetMessageNotFound() { 
		
		Message notFound = service.getMessage("notFound");
		try { 
			Hibernate.initialize(notFound);
		} catch (ObjectNotFoundException e) { 
			throw new NoDataFoundException();
		}
		
	}

}
