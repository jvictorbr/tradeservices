package com.citi.tradeservices.service.tlmonitor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.hamcrest.CoreMatchers.*;

import javax.transaction.Transactional;






import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@ActiveProfiles(value="Test")
public class TradeLayerMonitorRESTTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;	
	
	@Before
	public void setUp() { 
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testGetAllMessages() throws Exception { 
		
		mockMvc
			.perform(
					get("/tlmonitor/messages")	
					.accept(MediaType.APPLICATION_JSON_VALUE)					
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.messages", hasSize(2)))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void getGetErrorMessages() throws Exception { 
		
		mockMvc
			.perform(
					get("/tlmonitor/messages")	
					.param("isError", "true")
					.accept(MediaType.APPLICATION_JSON_VALUE)					
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0].requestId", is(equalTo("DUMMY_0202"))))
			.andExpect(jsonPath("$.messages[0].isError", is(equalTo(true))))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void getSuccessMessages() throws Exception { 
		
		mockMvc
			.perform(
					get("/tlmonitor/messages")	
					.param("isError", "false")
					.accept(MediaType.APPLICATION_JSON_VALUE)					
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0].requestId", is(equalTo("DUMMY_0101"))))
			.andExpect(jsonPath("$.messages[0].isError", is(equalTo(false))))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testNoMessages() throws Exception { 
		
		mockMvc
			.perform(
					get("/tlmonitor/messages")
					.param("from", "01/01/1901")
					.param("to", "01/02/1901")
					.accept(MediaType.APPLICATION_JSON_VALUE)
				)
			.andExpect(status().is4xxClientError())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.url", is(equalTo("/tlmonitor/messages"))))
			.andExpect(jsonPath("$.error", is(equalTo("No records could be found"))))
			.andDo(MockMvcResultHandlers.print());			
		
	}
	
	@Test
	public void testNotAcceptedContentType() throws Exception {
		
		mockMvc
			.perform(
				get("/tlmonitor/messages")
				.accept(MediaType.APPLICATION_ATOM_XML_VALUE)
					)
			.andExpect(status().is5xxServerError())
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testGetSuccessMessageDetails() throws Exception {
		
		mockMvc
			.perform(
					get("/tlmonitor/messageDetails/DUMMY_0101")
					.accept(MediaType.APPLICATION_JSON_VALUE)
			)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.messageDetails", hasSize(2)))
			.andExpect(jsonPath("$.messageDetails[0].requestId", is(equalTo("DUMMY_0101"))))
			.andExpect(jsonPath("$.messageDetails[0].messageType", is(equalTo("update-payment-order"))))
			.andExpect(jsonPath("$.messageDetails[1].requestId", is(equalTo("msg_response_DUMMY_0101"))))
			.andExpect(jsonPath("$.messageDetails[1].messageType", is(equalTo("update-payment-order-response"))))
			.andDo(MockMvcResultHandlers.print());	
		
	}
	
	@Test
	public void testGetErrorMessageDetails() throws Exception { 
		
		mockMvc
			.perform(
					get("/tlmonitor/messageDetails/DUMMY_0202")					
					.accept(MediaType.APPLICATION_JSON_VALUE)
			)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.messageDetails", hasSize(2)))
			.andExpect(jsonPath("$.messageDetails[0].requestId", is(equalTo("DUMMY_0202"))))
			.andExpect(jsonPath("$.messageDetails[0].messageType", is(equalTo("pay-contract"))))
			.andExpect(jsonPath("$.messageDetails[1].requestId", is(equalTo("msg_response_DUMMY_0202"))))
			.andExpect(jsonPath("$.messageDetails[1].messageType", is(equalTo("pay-contract-response"))))
			.andDo(MockMvcResultHandlers.print());
				
	}
	
	@Test
	public void testGetNoMessageDetails() throws Exception {
		mockMvc
			.perform(
					get("/tlmonitor/messageDetails/NotExistantReqId")					
					.accept(MediaType.APPLICATION_JSON_VALUE)
		)
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.url", is(equalTo("/tlmonitor/messageDetails/NotExistantReqId"))))
		.andExpect(jsonPath("$.error", is(equalTo("No records could be found"))))
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	
	@Test
	public void testGetMessage() throws Exception {
		
		mockMvc
			.perform(
				get("/tlmonitor/message/DUMMY_0101")
				.accept(MediaType.APPLICATION_JSON_VALUE)
			)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.requestId", is(equalTo("DUMMY_0101"))))
			.andExpect(jsonPath("$.messageCode", is(equalTo(100))))
			.andExpect(jsonPath("$.dealId", is(equalTo(1001))))
			.andExpect(jsonPath("$.bacenContractId", is(equalTo(1002))))
			.andExpect(jsonPath("$.bacenContractYear", is(equalTo(15))))
			.andExpect(jsonPath("$.fxContractId", is(equalTo(1003))))
			.andExpect(jsonPath("$.messageDescription", is(equalTo("Sucesso."))))
			.andExpect(jsonPath("$.messageDate", is(notNullValue())))
			.andExpect(jsonPath("$.isError", is(equalTo(false))))
			.andDo(MockMvcResultHandlers.print());
	
	}
	
	@Test
	public void testGetMessageNotFound() throws Exception { 
		
		mockMvc
			.perform(
				get("/tlmonitor/message/notFound")
				.accept(MediaType.APPLICATION_JSON_VALUE)
			)
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.url", is(equalTo("/tlmonitor/message/notFound"))))
			.andExpect(jsonPath("$.error", is(equalTo("No records could be found"))))
			.andDo(MockMvcResultHandlers.print());
		
	}
	

}

