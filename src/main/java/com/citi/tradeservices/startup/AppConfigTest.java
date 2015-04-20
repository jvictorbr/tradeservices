package com.citi.tradeservices.startup;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.citi.tradeservices.dao", "com.citi.tradeservices.service"})
@EnableTransactionManagement
@Profile("Test")
public class AppConfigTest {
	
	@Bean
    public SessionFactory sessionFactory() {
            LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
           builder.scanPackages("com.citi.tradeservices.domain").addProperties(getHibernateProperties());            
            return builder.buildSessionFactory();
    }	
	
	@Bean
    public HibernateTransactionManager txManager() {
            return new HibernateTransactionManager(sessionFactory());
    }
	
	@Bean
	public HibernateTemplate hibernateTemplate() { 
		return new HibernateTemplate(sessionFactory());
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() { 
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("db-messages.sql")
			.build();

	}

	protected Properties getHibernateProperties() {		
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		return prop;		
	}
	
	
	
	

}
