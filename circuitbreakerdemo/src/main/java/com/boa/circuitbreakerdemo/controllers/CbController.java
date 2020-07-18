package com.boa.circuitbreakerdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boa.circuitbreakerdemo.dto.JwtRequest;
import com.boa.circuitbreakerdemo.handlers.HystrixHandler;

@RestController
public class CbController {
    @Autowired 
	private HystrixHandler hystrixHandler;
	
	@PostMapping("/cbcustomers")
	public ResponseEntity<?> getCustomers(@RequestBody JwtRequest jwtRequest)
	{
		ResponseEntity<String> response=hystrixHandler.requestHandler(jwtRequest);
		
		if(response.getBody().isEmpty())
			
	       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Call not connected");
		else
			return response;
		
	}
}
