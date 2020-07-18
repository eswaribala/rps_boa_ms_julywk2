package com.boa.customerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boa.customerapi.dtos.CustomerResponse;
import com.boa.customerapi.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	//jpql
	
    @Query("select c.customerId,c.name from Customer c where c.mobileNo=:mobileNo")
	public CustomerResponse findByMobileNo(@Param("mobileNo") Long mobileNo);
		

	
}
