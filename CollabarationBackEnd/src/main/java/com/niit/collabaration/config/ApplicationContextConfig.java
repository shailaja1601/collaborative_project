package com.niit.collabaration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collabaration.model.Blog;
import com.niit.collabaration.model.Friend;
import com.niit.collabaration.model.Job;
import com.niit.collabaration.model.JobApplication;
import com.niit.collabaration.model.User;

@Configuration
@ComponentScan("com.niit.*")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	 @Bean(name="dataSource")
	 public DataSource getOracleDataSource(){
	       //DriverManagerDataSource dataSource=new DriverManagerDataSource();
	      DriverManagerDataSource dataSource=new DriverManagerDataSource();
		  dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	       dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	       dataSource.setUsername("COL_DB");//schema name COL_DB 
	       dataSource.setPassword("coldb");//password coldb
	       
	       Properties connectionProperties=new Properties();
	       
	       connectionProperties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
	       dataSource.setConnectionProperties(connectionProperties);
	       return dataSource;
}
	 @Autowired
	 @Bean(name="sessionFactory")
	 public SessionFactory getsessionFactory(DataSource dataSource){
		 LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
		 //sessionBuilder.addProperties(getHibernateProperties());
	    	     sessionBuilder.addAnnotatedClass(User.class);
	    	     sessionBuilder.addAnnotatedClass(Job.class);
	    	     sessionBuilder.addAnnotatedClass(JobApplication.class);
	    	     sessionBuilder.addAnnotatedClass(Friend.class);
	    	     sessionBuilder.addAnnotatedClass(Blog.class);
	    	     
	       return sessionBuilder.buildSessionFactory();
	 }
	 @Autowired
	 @Bean(name="transactionManager")
	  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		           HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		              return transactionManager;
	 }
}
