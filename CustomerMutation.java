package com.boa.customer.mutations;

import com.boa.customer.models.Customer;
import com.boa.customer.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomerMutation implements GraphQLMutationResolver {

    @Autowired
    private CustomerService customerService;

    public Customer createCustomer(final String firstName, final String lastName,final String emailId, final long mobileNo , final String dob, final String gender) {
        return this.customerService.createVehicle(firstName,lastName,emailId,mobileNo,dob,gender);
    }
}
