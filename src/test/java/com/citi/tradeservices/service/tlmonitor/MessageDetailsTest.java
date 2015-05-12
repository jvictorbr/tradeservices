package com.citi.tradeservices.service.tlmonitor;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.citi.tradeservices.domain.tlmonitor.MessageDetails;
import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@ActiveProfiles(value="Test")
public class MessageDetailsTest {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Test
	public void testLoadSuccessMessages() {
		
		assertThat(hibernateTemplate, notNullValue());
		
		MessageDetails messageDetails01 = hibernateTemplate.load(MessageDetails.class, "DUMMY_0101");
		
		assertThat(messageDetails01, notNullValue());
		assertThat(messageDetails01.getRequestId(), allOf(notNullValue(), equalTo("DUMMY_0101")));		
		assertThat(messageDetails01.getMessageType(), allOf(notNullValue(), equalTo("update-payment-order")));
		assertThat(messageDetails01.getMessageDate(), notNullValue());
		assertThat(messageDetails01.getMessageContent().contains("<msg_pk>DUMMY_0101</msg_pk>"), is(true));
			
		
		MessageDetails messageDetails02 = hibernateTemplate.load(MessageDetails.class, "msg_response_DUMMY_0101");
		
		assertThat(messageDetails02, notNullValue());
		assertThat(messageDetails02.getRequestId(), allOf(notNullValue(), equalTo("msg_response_DUMMY_0101")));		
		assertThat(messageDetails02.getMessageType(), allOf(notNullValue(), equalTo("update-payment-order-response")));
		assertThat(messageDetails02.getMessageDate(), notNullValue());
		assertThat(messageDetails02.getMessageContent().contains("<msg_pk>msg_response_DUMMY_0101</msg_pk>"), is(true));
		assertThat(messageDetails02.getMessageContent().contains("<reference_msg_pk>DUMMY_0101</reference_msg_pk>"), is(true));
		assertThat(messageDetails02.getMessageContent().contains("<error>"), is(false));
		
	}
	
	@Test
	public void testLoadErrorMessage() {
		
		assertThat(hibernateTemplate, notNullValue());
		
		MessageDetails messageDetails01 = hibernateTemplate.load(MessageDetails.class, "DUMMY_0202");
		
		assertThat(messageDetails01, notNullValue());
		assertThat(messageDetails01.getRequestId(), allOf(notNullValue(), equalTo("DUMMY_0202")));		
		assertThat(messageDetails01.getMessageType(), allOf(notNullValue(), equalTo("pay-contract")));
		assertThat(messageDetails01.getMessageDate(), notNullValue());
		assertThat(messageDetails01.getMessageContent().contains("<msg_pk>DUMMY_0202</msg_pk>"), is(true));		
		
		
		MessageDetails messageDetails02 = hibernateTemplate.load(MessageDetails.class, "msg_response_DUMMY_0202");
		
		assertThat(messageDetails02, notNullValue());
		assertThat(messageDetails02.getRequestId(), allOf(notNullValue(), equalTo("msg_response_DUMMY_0202")));		
		assertThat(messageDetails02.getMessageType(), allOf(notNullValue(), equalTo("pay-contract-response")));
		assertThat(messageDetails02.getMessageDate(), notNullValue());
		assertThat(messageDetails02.getMessageContent().contains("<msg_pk>msg_response_DUMMY_0202</msg_pk>"), is(true));
		assertThat(messageDetails02.getMessageContent().contains("<reference_msg_pk>DUMMY_0202</reference_msg_pk>"), is(true));
		assertThat(messageDetails02.getMessageContent().contains("<error>"), is(true));
		
	}	
	

}
