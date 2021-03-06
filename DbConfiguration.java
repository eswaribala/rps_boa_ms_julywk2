package com.boa.customerapi.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Data
@Slf4j
@EnableConfigurationProperties(VaultConfiguration.class)

public class DbConfiguration {
    //reading key from property file 
	@Value("${db_url}")
	private String url;
	@Value("${db_username}")
	private String userName;
	@Value("${db_password}")
	private String password;
	@Value("${db_driver}")
	private String driver;
	private DataSourceBuilder dbBuilder;
	
	private final VaultConfiguration vaultConfiguration;
	
	public DbConfiguration(VaultConfiguration vaultConfiguration)
	{
		this.vaultConfiguration=vaultConfiguration;
	}
	
	
	
	//definition
	@Bean
	@Profile("prod")
	public DataSource getProdDataSource()
	{
		 dbBuilder=DataSourceBuilder.create();
		 dbBuilder.url(url);
		 //dbBuilder.username(userName);
		 //dbBuilder.password(password);
		 
		    log.info("----------------------------------------");
		    log.info("Configuration properties");
		    log.info("   mysql.username is {}", vaultConfiguration.getUsername());
		    log.info("   mysql.password is {}", vaultConfiguration.getPassword());
		    log.info("----------------------------------------");
		    dbBuilder.username(vaultConfiguration.getUsername());
		    dbBuilder.password(vaultConfiguration.getPassword());
		 dbBuilder.driverClassName(driver);
		 log.info("Production env.........");
		 return dbBuilder.build();
		
	}
	
	@Bean	
	@Profile("dev")
	public DataSource getDevDataSource()
	{
		 dbBuilder=DataSourceBuilder.create();
		 dbBuilder.url(url);
		 log.info("----------------------------------------");
		    log.info("Configuration properties");
		    log.info("   mysql.username is {}", vaultConfiguration.getUsername());
		    log.info("   mysql.password is {}", vaultConfiguration.getPassword());
		    log.info("----------------------------------------");
		    dbBuilder.username(vaultConfiguration.getUsername());
		    dbBuilder.password(vaultConfiguration.getPassword());
		 dbBuilder.driverClassName(driver);
		 log.info("Development env.........");
		 return dbBuilder.build();
		
	}
	@Bean	
	@Profile("qa")
	public DataSource getQADataSource()
	{
		 dbBuilder=DataSourceBuilder.create();
		 dbBuilder.url(url);
		 log.info("----------------------------------------");
		    log.info("Configuration properties");
		    log.info("   mysql.username is {}", vaultConfiguration.getUsername());
		    log.info("   mysql.password is {}", vaultConfiguration.getPassword());
		    log.info("----------------------------------------");
		    dbBuilder.username(vaultConfiguration.getUsername());
		    dbBuilder.password(vaultConfiguration.getPassword());
		 dbBuilder.driverClassName(driver);
		 log.info("QA env.........");
		 return dbBuilder.build();
		
	}
	@Bean
	@Profile("test")
	public DataSource getTestDataSource()
	{
		 dbBuilder=DataSourceBuilder.create();
		 dbBuilder.url(url);
		 log.info("----------------------------------------");
		    log.info("Configuration properties");
		    log.info("   mysql.username is {}", vaultConfiguration.getUsername());
		    log.info("   mysql.password is {}", vaultConfiguration.getPassword());
		    log.info("----------------------------------------");
		    dbBuilder.username(vaultConfiguration.getUsername());
		    dbBuilder.password(vaultConfiguration.getPassword());
		 dbBuilder.driverClassName(driver);
		 log.info("Test env.........");
		 return dbBuilder.build();
		
	}
}
