package com.boa.customerapi.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BOA_Customer_Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Address_Id")
	private long addressId;
	@Column(name="Door_No",nullable = false,length = 5)
	private String doorNo;
	@Column(name="Street_Name",nullable = false,length = 100)
	private String streetName;
	@Column(name="City",nullable = false,length = 100)
	private String City;
	@Column(name="State",nullable = false,length = 100)
	private String State;
	@Column(name="Pincode")
	private long pincode;
	@ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.MERGE )
	@JoinColumn(name="Customer_Id")
	private Customer customer;
	

}
