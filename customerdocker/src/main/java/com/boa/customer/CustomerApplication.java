package com.boa.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.vault.config.SecretBackendConfigurer;
import org.springframework.cloud.vault.config.VaultConfigurer;
import org.springframework.context.annotation.Bean;*/
//@RefreshScope
@SpringBootApplication
@EnableAutoConfiguration(exclude =DataSourceAutoConfiguration.class)
//@EnableEurekaClient
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	
}
