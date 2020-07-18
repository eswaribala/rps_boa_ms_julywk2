package com.boa.customer.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boa.customer.models.Customer;
import com.boa.customer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	//post operation
	//add the customer
	@CrossOrigin("*")
	@PostMapping("/customers")
	public @ResponseBody Customer saveCustomer(@RequestBody Customer customer) 
	{
		return this.customerService.addCustomer(customer);
	}
	
	//get all customers
	@CrossOrigin("*")
	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers()
	{
		List<Customer> customerList=customerService.getAllCustomers();
		
		if(customerList.size()==0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerList);
	}
 	
	//get customer by id
	@CrossOrigin("*")
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") long customerId )
	{
		
		Customer customer=this.customerService.getCustomerById(customerId);
		
		if(customer!=null)
			return ResponseEntity.status(HttpStatus.FOUND).body(customer);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
		
		
	}
	
	
	//http://localhost:7070/customers/filters/1?fields=customerId
		@CrossOrigin("*")
		@GetMapping("/customers/filters/{customerId}")
		public ResponseEntity<?> findCustomerById(@RequestParam(name = "fields", required = false) 
	    String fields, @PathVariable("customerId") long customerId)
		{
			Map<Object, Object> model = new HashMap<>();
			Customer customer=this.customerService.getCustomerById(customerId);
			if(customer!=null)
			{
				
				ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
				return ResponseEntity.ok(SquigglyUtils.stringify(mapper, customer));
			}
			else
			{
				
	         model.put("message", "customer not existing");
	        
	         return ResponseEntity.ok(model);
			}
		}

	

}
