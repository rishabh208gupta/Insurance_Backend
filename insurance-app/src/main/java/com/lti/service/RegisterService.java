package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lti.dao.CustomerDao;
import com.lti.entity.Customer;
import com.lti.exception.RegisterServiceException;

public class RegisterService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public int registeration(Customer customer) {
		if (!customerDao.isCustomerPresent(customer.getEmail())) {
			int id = customerDao.register(customer);
			//emailService.sendMailForNewRegistration(customer);
			return id;
//			then code to send an email to the customer will be here !!
		} else {
			throw new RegisterServiceException("Customer Already Registered");
		}
	}
	
}
