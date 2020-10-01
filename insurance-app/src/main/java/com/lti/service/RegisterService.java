package com.lti.service;

public class RegisterService {
	
	@Override
	public int register(Customer customer) {
		if (!customerRepository.isCustomerPresent(customer.getEmail())) {
			int id = customerRepository.save(customer);
			emailService.sendMailForNewRegistration(customer);
			return id;
//			then code to send an email to the customer will be here !!
		} else {
			throw new CustomerServiceException("Customer Already Registered");
		}
	}

}
