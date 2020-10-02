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
	
	public String registeration(Customer customer) {
		if (!customerDao.isCustomerPresent(customer.getEmail())) {
			customer = customerDao.register(customer);
		//	int referenceNo = customerDao.findVisitorByEmail(customer.getEmail());
		//	System.out.println(referenceNo);
		//	String info = "Congratulations for registration. Your registration id is " + referenceNo;
			String email = customer.getEmail();
		//	emailService.Mailer(email, info);
			return "Customer registration sucessfull";
		} else {
			throw new RegisterServiceException("Customer Already Registered");
		}
	}
	
}
