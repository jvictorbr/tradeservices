package com.citi.tradservices.service.callback;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;
import com.citi.tradeservices.exception.NoDataFoundException;
import com.citi.tradeservices.service.callback.CallbackService;
import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;
import com.citi.tradeservices.webservices.CallbackREST;
import com.citi.tradeservices.webservices.GlobalExceptionHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@ActiveProfiles(value="Test")
public class CallbackRESTTest {
	
	@InjectMocks
	private CallbackREST callbackRESTController = new CallbackREST();
		
	@Mock
	private CallbackService service;
		
	private MockMvc mockMvc;	
	
	@Before
	public void setUp() { 
		MockitoAnnotations.initMocks(this);
		
		/* Register global exception handler. */ 
		final StaticApplicationContext applicationContext = new StaticApplicationContext();
		applicationContext.registerSingleton("exceptionHandler", GlobalExceptionHandler.class);
		final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
		webMvcConfigurationSupport.setApplicationContext(applicationContext);
		
		this.mockMvc = standaloneSetup(callbackRESTController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
				.setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
				.build();
		
		final String cnpj = "/08049872000194";
		final String account = "/126459";
		final CallbackRequest request = new CallbackRequest(cnpj, account);
		
		when(service.getCallback((CallbackRequest) anyObject())).thenAnswer(new Answer<Callback>() {
			@Override
			public Callback answer(InvocationOnMock invocation) throws Throwable {
				CallbackRequest mockRequest = (CallbackRequest) invocation.getArguments()[0];
				if (mockRequest.equals(request)) { 
					return CallbackMockBuilder.getMockCallback(request);
				} else { 
					throw new NoDataFoundException();
				}
			} 
		});
		//
		
	}
	

	
	@Test
	public void testGetCallback() throws Exception { 
		
		final String cnpj = "/08049872000194";
		final String account = "/126459";
		
		mockMvc
			.perform(
				get("/callback")
				.param("cnpj", cnpj)
				.param("account", account)
				.accept(MediaType.APPLICATION_JSON_VALUE)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customerId", is(equalTo(cnpj))))
			.andExpect(jsonPath("$.account", is(equalTo(account))))
			.andExpect(jsonPath("$.intermediaryInstitution.bicAccount", is(equalTo("BICACCOUNT3"))))
			.andDo(MockMvcResultHandlers.print());		
		
	}
	
	@Test
	public void testGetCallbackNotFound() throws Exception { 
		
		final String cnpj = "/BLABLA";
		final String account = "/BLABLA";
		
		mockMvc
			.perform(
				get("/callback")
				.param("cnpj", cnpj)
				.param("account", account)
				.accept(MediaType.APPLICATION_JSON_VALUE)
			)
			.andExpect(status().isNotFound())
			.andDo(MockMvcResultHandlers.print());		
		
	}
	
	

}
