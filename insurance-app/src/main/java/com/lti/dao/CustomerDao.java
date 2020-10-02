package com.lti.dao;

import com.lti.entity.Customer;

public interface CustomerDao {
	
	int register(Customer customer);
	
	Customer findById(int id);
	
	int findByEmailAndPassword(String email, String password);
	
	boolean isCustomerPresent(String email);

}
