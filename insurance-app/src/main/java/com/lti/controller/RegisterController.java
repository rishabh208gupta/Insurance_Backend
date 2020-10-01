package com.lti.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Registration;
import com.lti.dto.Status;
import com.lti.entity.Customer;
import com.lti.exception.RegisterServiceException;
import com.lti.service.RegisterService;

@RestController
@CrossOrigin
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping(path = "/register")
	public Status registerCustomer(@RequestBody Registration registration) {
		Customer customer = new Customer(); 
		BeanUtils.copyProperties(registration, customer);
		try {
			int id = registerService.registeration(customer);

			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Registration Successful");
			return status;
		} catch (RegisterServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}

	}

}
