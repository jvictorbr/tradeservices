package com.citi.tradeservices.startup;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.citi.tradeservices.dao", "com.citi.tradeservices.service"})
@EnableTransactionManagement
public class AppConfig {
	
	private static final transient Logger log = Logger.getLogger(AppConfig.class);
	
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
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		JndiTemplate jndi = new JndiTemplate();
		DataSource dataSource = null;
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/TradeDS");
        } catch (NamingException e) {
            log.error("NamingException for java:comp/env/jdbc/yourname", e);
        }
        return dataSource;
	}
	
	
	protected Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		return prop;
	}




}
