package com.citi.tradeservices.startup;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan({"com.citi.tradeservices.webservices"})
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	
	@Bean
	public RequestMappingHandlerAdapter getRequestMappingHandler() {
		RequestMappingHandlerAdapter requestMappingHandler = new RequestMappingHandlerAdapter();
		
		requestMappingHandler.getMessageConverters().add(new MarshallingHttpMessageConverter(new XStreamMarshaller()));
		requestMappingHandler.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		return requestMappingHandler;
	}

	
	
}
