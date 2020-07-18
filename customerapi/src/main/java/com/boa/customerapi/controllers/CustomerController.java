package com.boa.customerapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;

@RestController
public class CustomerController {
    @Autowired
	private CustomerService customerService;
    
    //api path
    //create the customer
    @CrossOrigin("*")
    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
    {
    	Customer customerObj=this.customerService.addCustomer(customer);
    	if(customerObj==null)
    	{
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Created");
    		
    	}
    	else
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
    }
     
    //retrieving customer
    @CrossOrigin("*")
    @GetMapping("/customers")
    public List<Customer> getAllCustomers()
    {
    	return this.customerService.getAllCustomers();
    }
    
    //retrieve customer by id
    @CrossOrigin("*")
    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") long customerId)
    {
    	return this.customerService.getCustomerById(customerId);
    }
    
    //delete the customer
    @CrossOrigin("*")
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId)
    {
    	if(this.customerService.deleteByCustomerId(customerId))
    	{
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer Deleted");
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not Deleted");
    	
    }
    //update the customer
    @CrossOrigin("*")
    @PutMapping("/customers")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer)
    {
    	Customer customerObj=this.customerService.updateCustomer(customer);
    	if(customerObj==null)
    	{
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not updated");
    		
    	}
    	else
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
    }
    
    //filtering
  //http://localhost:7070/customers/filters/1?fields=customerId,name
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
