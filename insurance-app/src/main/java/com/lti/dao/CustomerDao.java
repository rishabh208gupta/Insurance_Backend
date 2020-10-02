package com.lti.dao;

import com.lti.entity.Customer;

public interface CustomerDao {
	
	int register(Customer customer);
	
	Customer findById(int id);
	
	boolean isCustomerPresent(String email);

}
