package com.citi.tradservices.service.callback;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.citi.tradeservices.dao.callback.CallbackDAO;
import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;
import com.citi.tradeservices.service.callback.CallbackService;
import com.citi.tradeservices.service.callback.CallbackServiceImpl;
import com.citi.tradeservices.startup.AppConfigTest;
import com.citi.tradeservices.startup.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class, WebMvcConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@ActiveProfiles(value="Test")
public class CallbackServiceTest {
	
	@InjectMocks
	private CallbackService service = new CallbackServiceImpl();
	
	@Mock
	private CallbackDAO dao;
	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	
	@Test
	public void testGetCallback() {
		
		final String cnpj = "/08049872000194";
		final String account = "/126459";
		
		CallbackRequest request = new CallbackRequest();
		request.setCustomerCNPJ(cnpj);
		request.setAccount(account);
		
		when(dao.getCallback(request)).thenReturn(CallbackMockBuilder.getMockCallback(request));
		
		Callback callback = service.getCallback(request);
		
		assertThat(callback, notNullValue());
		assertThat(callback.getId(), is(equalTo(request.getId())));
		assertThat(callback.getAccount(), is(equalTo(request.getAccount())));
		assertThat(callback.getCustomerId(), is(equalTo(request.getCustomerCNPJ())));
		
		assertThat(callback.getAccountInstitution(), notNullValue());
		assertThat(callback.getAccountInstitution().getBankName(), is(equalTo("BANK1")));
		assertThat(callback.getAccountInstitution().getBicAccount(), is(equalTo("BICACCOUNT1")));
		assertThat(callback.getAccountInstitution().getBicCode(), is(equalTo("BICCODE1")));
		assertThat(callback.getAccountInstitution().getCcClear(), is(equalTo("CCLEAR1")));
		assertThat(callback.getAccountInstitution().getAddresses(), is(notNullValue()));
		assertThat(callback.getAccountInstitution().getAddresses().size(), is(equalTo(3)));
		assertThat(callback.getAccountInstitution().getAddresses().get(0), is(equalTo("AI_ADDRESS1")));
		assertThat(callback.getAccountInstitution().getAddresses().get(1), is(equalTo("AI_ADDRESS2")));
		assertThat(callback.getAccountInstitution().getAddresses().get(2), is(equalTo("AI_ADDRESS3")));
		
		assertThat(callback.getBeneficiaryCustomer(), notNullValue());
		assertThat(callback.getBeneficiaryCustomer().getBankName(), is(equalTo("BANK2")));
		assertThat(callback.getBeneficiaryCustomer().getBicAccount(), is(equalTo("BICACCOUNT2")));
		assertThat(callback.getBeneficiaryCustomer().getBicCode(), is(equalTo("BICCODE2")));
		assertThat(callback.getBeneficiaryCustomer().getCcClear(), is(equalTo("CCLEAR2")));
		assertThat(callback.getBeneficiaryCustomer().getAddresses(), is(notNullValue()));
		assertThat(callback.getBeneficiaryCustomer().getAddresses().size(), is(equalTo(3)));
		assertThat(callback.getBeneficiaryCustomer().getAddresses().get(0), is(equalTo("BC_ADDRESS1")));
		assertThat(callback.getBeneficiaryCustomer().getAddresses().get(1), is(equalTo("BC_ADDRESS2")));
		assertThat(callback.getBeneficiaryCustomer().getAddresses().get(2), is(equalTo("BC_ADDRESS3")));
		
		assertThat(callback.getIntermediaryInstitution(), notNullValue());
		assertThat(callback.getIntermediaryInstitution().getBankName(), is(equalTo("BANK3")));
		assertThat(callback.getIntermediaryInstitution().getBicAccount(), is(equalTo("BICACCOUNT3")));
		assertThat(callback.getIntermediaryInstitution().getBicCode(), is(equalTo("BICCODE3")));
		assertThat(callback.getIntermediaryInstitution().getCcClear(), is(equalTo("CCLEAR3")));
		assertThat(callback.getIntermediaryInstitution().getAddresses(), is(notNullValue()));
		assertThat(callback.getIntermediaryInstitution().getAddresses().size(), is(equalTo(3)));
		assertThat(callback.getIntermediaryInstitution().getAddresses().get(0), is(equalTo("II_ADDRESS1")));
		assertThat(callback.getIntermediaryInstitution().getAddresses().get(1), is(equalTo("II_ADDRESS2")));
		assertThat(callback.getIntermediaryInstitution().getAddresses().get(2), is(equalTo("II_ADDRESS3")));
		
		assertThat(callback.getOrderCount(), is(equalTo(10)));
		assertThat(callback.getLastOrderDate(), is(notNullValue()));
		
	}

}
