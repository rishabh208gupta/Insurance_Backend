package com.lti.dao;

import com.lti.entity.Customer;

public interface ResetDao {

	Customer getCustomerByMailId(String emailId);

	void updateCustomer(Customer customer);

}
