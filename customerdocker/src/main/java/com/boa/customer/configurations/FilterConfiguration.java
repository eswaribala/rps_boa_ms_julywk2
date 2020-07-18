package com.boa.customer.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.boa.customer.filters.CustomerBearerFilter;
import com.boa.customer.filters.CustomerIPFilter;

@Configuration
public class FilterConfiguration {
	
	private FilterRegistrationBean filterRegistrationBean;
	
	@Bean
	public FilterRegistrationBean getCustomerIPFilter()
	{
		filterRegistrationBean=new FilterRegistrationBean();
		CustomerIPFilter customerFilter=new CustomerIPFilter();
		filterRegistrationBean.setFilter(customerFilter);
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/customers/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean getCustomerBearerFilter()
	{
		filterRegistrationBean =new FilterRegistrationBean();
		CustomerBearerFilter customerBearerFilter=new CustomerBearerFilter();
		filterRegistrationBean.setFilter(customerBearerFilter);
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/customers/*");
		return filterRegistrationBean;
	}

}
