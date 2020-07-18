package com.boa.customer.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boa.customer.models.Customer;
import com.boa.customer.repositories.CustomerRepository;
import com.boa.customer.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;



@Component
public class CustomerResolver implements GraphQLQueryResolver {
	 @Autowired
	 private CustomerService customerService;
	
	 
	    public List<Customer> findAllCustomers() {
	        return this.customerService.getAllCustomers();
	    }

	    public Customer findCustomer(final int id) {
	        return this.customerService.getCustomerById(id);
	    }

  

}