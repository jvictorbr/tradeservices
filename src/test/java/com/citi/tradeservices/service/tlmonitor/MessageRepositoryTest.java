package com.citi.tradeservices.service.tlmonitor;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.hamcrest.beans.HasPropertyWithValue.*;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.citi.tradeservices.dao.tlmonitor.MessageDAO;
import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.domain.tlmonitor.MessageDetails;
import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@ActiveProfiles(value="Test")
public class MessageRepositoryTest {
	
	@Autowired
	private MessageDAO dao;
	
	@Test
	public void testGetAllMessages() {
		
		List<Message> allMessages = dao.getMessages(null, null, null);
		
		assertThat(allMessages, is(notNullValue()));
		assertThat(allMessages.size(), is(equalTo(2)));	
	
	}
	
	@Test
	public void testGetSuccessMessage() { 
		
		List<Message> successMessages = dao.getMessages(Boolean.FALSE, null, null);
		
		assertThat(successMessages, is(notNullValue()));
		assertThat(successMessages.size(), is(equalTo(1)));
		
		Message successMessage = successMessages.get(0);
		
		assertThat(successMessage, is(notNullValue()));
		assertThat(successMessage.getRequestId(), is(equalTo("DUMMY_0101")));
		assertThat(successMessage.getIsError(), is(equalTo(false)));
				
	}
	
	@Test
	public void testGetSuccessMessageWithDates() {
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, +1);
		
		List<Message> successMessages = dao.getMessages(Boolean.FALSE, yesterday.getTime(), tomorrow.getTime());
		
		assertThat(successMessages, allOf( is(notNullValue()),	hasSize(1)	));
		
		Message successMessage = successMessages.get(0);
		
		assertThat(successMessage, is(notNullValue()));
		assertThat(successMessage.getRequestId(), is(equalTo("DUMMY_0101")));
		assertThat(successMessage.getIsError(), is(equalTo(false)));
		
	}
	
	@Test
	public void testGetErrorMessagesWithDates() { 
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, +1);
		
		List<Message> errorMessages = dao.getMessages(Boolean.TRUE, yesterday.getTime(), tomorrow.getTime());
		
		assertThat(errorMessages, allOf( is(notNullValue()),	hasSize(1)	));
		
		Message successMessage = errorMessages.get(0);
		
		assertThat(successMessage, is(notNullValue()));
		assertThat(successMessage.getRequestId(), is(equalTo("DUMMY_0202")));
		assertThat(successMessage.getIsError(), is(equalTo(true)));
		
	}
	
	@Test
	public void testGetMessagesWithFromDateOnly() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		
		List<Message> messages = dao.getMessages(null, yesterday.getTime(), null);
		
		assertThat(messages, allOf(
				is(notNullValue()),
				hasSize(2)
		));
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, +1);
		
		List<Message> noMessages = dao.getMessages(null, tomorrow.getTime(), null);
		
		assertThat(noMessages, allOf(
				is(notNullValue()),
				hasSize(0)
		));
		
	}
	
	@Test
	public void testGetMessagesWithToDateOnly() { 
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, +1);
		
		List<Message> messages = dao.getMessages(null, null, tomorrow.getTime());
		
		assertThat(messages, allOf(
				is(notNullValue()),
				hasSize(2)
		));
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		
		List<Message> noMessages = dao.getMessages(null, null, yesterday.getTime());
		
		assertThat(noMessages, allOf(
				is(notNullValue()),
				hasSize(0)
		));
	}
	
	@Test
	public void testGetNoMessagesWithDates() {
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		
		List<Message> noMessages = dao.getMessages(null, yesterday.getTime(), yesterday.getTime());
		
		assertThat(noMessages, allOf(	is(notNullValue()),		hasSize(0)	));
				
	}
	
	@Test
	public void testGetErrorMessage() {
		
		List<Message> errorMessages = dao.getMessages(Boolean.TRUE, null, null);
		
		assertThat(errorMessages, is(notNullValue()));
		assertThat(errorMessages.size(), is(equalTo(1)));
		
		Message errorMessage = errorMessages.get(0);
		
		assertThat(errorMessage, is(notNullValue()));
		assertThat(errorMessage.getRequestId(), is(equalTo("DUMMY_0202")));
		assertThat(errorMessage.getIsError(), is(equalTo(true)));
		
	}	
	
	@Test
	public void testGetMessageDetailsSuccess() { 
		
		List<MessageDetails> listMessageDetails01 = dao.getMessageDetails("DUMMY_0101");		
		assertThat(listMessageDetails01, notNullValue());
		assertThat(listMessageDetails01.isEmpty(), is(false));
		assertThat(listMessageDetails01.size(), is((equalTo(2))));
		
		MessageDetails requestMessage01 = listMessageDetails01.get(0);
		assertThat(requestMessage01, notNullValue());
		assertThat(requestMessage01.getRequestId(), allOf(notNullValue(), is(equalTo("DUMMY_0101"))));
		assertThat(requestMessage01.getMessageType(), allOf(notNullValue(), is(equalTo("update-payment-order"))));
		assertThat(requestMessage01.getMessageDate(), notNullValue());
		assertThat(requestMessage01.getMessageContent().contains("<msg_pk>DUMMY_0101</msg_pk>"), is(true));
		
		MessageDetails responseMessage01 = listMessageDetails01.get(1);
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
		
		List<MessageDetails> listMessageDetails02 = dao.getMessageDetails("DUMMY_0202");		
		assertThat(listMessageDetails02, notNullValue());
		assertThat(listMessageDetails02.isEmpty(), is(false));
		assertThat(listMessageDetails02.size(), is((equalTo(2))));
		
		MessageDetails requestMessage02 = listMessageDetails02.get(0);
		assertThat(requestMessage02, notNullValue());
		assertThat(requestMessage02.getRequestId(), allOf(notNullValue(), is(equalTo("DUMMY_0202"))));
		assertThat(requestMessage02.getMessageType(), allOf(notNullValue(), is(equalTo("pay-contract"))));
		assertThat(requestMessage02.getMessageDate(), notNullValue());
		assertThat(requestMessage02.getMessageContent().contains("<msg_pk>DUMMY_0202</msg_pk>"), is(true));
		
		MessageDetails responseMessage02 = listMessageDetails02.get(1);
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
		
		List<MessageDetails> emptyMessageDetails = dao.getMessageDetails("NotExistant");
		
		assertThat(emptyMessageDetails, notNullValue());
		assertThat(emptyMessageDetails.isEmpty(), is(equalTo(true)));
		assertThat(emptyMessageDetails.size(), is(equalTo(0)));
		
	}
	

}
