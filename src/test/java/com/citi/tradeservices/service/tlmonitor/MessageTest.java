package com.citi.tradeservices.service.tlmonitor;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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

import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@ActiveProfiles(value="Test")
public class MessageTest {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Test
	public void testLoadSucessMessage() { 
		
		
		assertThat(hibernateTemplate, notNullValue());
		
		Message message = hibernateTemplate.load(Message.class, "DUMMY_0101");
		
		assertThat(message, notNullValue());				
		assertThat(message.getIsError(), is(equalTo(false)));
		assertThat(message.getRequestId(), is(equalTo("DUMMY_0101")));
		assertThat(message.getDealId(), is(equalTo(1001L)));
		assertThat(message.getBacenContractId(), is(equalTo(1002L)));
		assertThat(message.getBacenContractYear(), is(equalTo(15)));
		assertThat(message.getFxContractId(), is(equalTo(1003L)));
		assertThat(message.getMessageDescription(), is(equalTo("Sucesso.")));
		assertThat(message.getMessageDate(), is(notNullValue()));
			
	}
	
	@Test
	public void testLoadErrorMessage() { 
		
		assertThat(hibernateTemplate, notNullValue());
		
		Message message = hibernateTemplate.load(Message.class, "DUMMY_0202");
		
		assertThat(message, notNullValue());
		assertThat(message.getIsError(), is(equalTo(true)));
		assertThat(message.getRequestId(), is(equalTo("DUMMY_0202")));
		assertThat(message.getDealId(), is(equalTo(2001L)));
		assertThat(message.getBacenContractId(), is(equalTo(2002L)));
		assertThat(message.getBacenContractYear(), is(equalTo(15)));
		assertThat(message.getFxContractId(), is(equalTo(2003L)));
		assertThat(message.getMessageDescription(), is(equalTo("Erro.")));
		assertThat(message.getMessageDate(), is(notNullValue()));		
		
	}

}
