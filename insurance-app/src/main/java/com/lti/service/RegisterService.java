package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CustomerDao;
import com.lti.entity.Customer;
import com.lti.exception.RegisterServiceException;

@Service
public class RegisterService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private EmailService emailService;
	
	public int registeration(Customer customer) {
		if (!customerDao.isCustomerPresent(customer.getEmail())) {
			int id = customerDao.register(customer);
			emailService.sendMailForNewRegistration(customer);
			return id;
		} else {
			throw new RegisterServiceException("Customer Already Registered");
		}
	}
	
}
