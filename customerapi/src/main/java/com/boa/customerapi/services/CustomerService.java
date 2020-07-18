package com.boa.customerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.boa.customerapi.dtos.CustomerResponse;
import com.boa.customerapi.models.Customer;
import com.boa.customerapi.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
    @Value("${message}")
	private String message;
	
	//insert
	
	public Customer addCustomer(Customer customer)
	{
		log.info(message);
		return this.customerRepository.save(customer);
	}
	
	
	//select all
	
	public List<Customer> getAllCustomers()
	{
		return this.customerRepository.findAll();
	}
	
	//select by id
	
	public Customer getCustomerById(long customerId)
	{
		return this.customerRepository.findById(customerId).orElse(null);
	}
	//update
	
	public Customer updateCustomer(Customer customer)
	{
		return this.customerRepository.save(customer);
	}
	
	
	//delete by id
	
	public boolean deleteByCustomerId (long customerId)
	{
		boolean status=false;
		 this.customerRepository.deleteById(customerId);
		 Customer customer=getCustomerById(customerId);
		 if (customer==null)
			 status=true;
		 return status;
		 
	}
	
	
	//filter by mobileNo
	
	public CustomerResponse getCustomerByMobileNo(long mobileNo)
	{
		return this.customerRepository.findByMobileNo(mobileNo);
	}
	
}
