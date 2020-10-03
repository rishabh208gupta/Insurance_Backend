package com.lti.dao;

import com.lti.entity.Customer;

public interface CustomerDao {
	
	Customer register(Customer customer);
	
	Customer findById(int id);
	
	Customer findByEmail(String email);
	
	boolean isCustomerPresent(String email);

}
