package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.entity.Customer;
import com.lti.exception.LoginServiceException;
import com.lti.service.LoginService;


public class LoginController {
	
	@Autowired
	private LoginService loginService;
	

	@PostMapping(path="/login") 
	public LoginStatus login(@RequestBody Login login) {
		try {
			Customer customer=loginService.login(login.getEmail(), login.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			
			return loginStatus;
			
		}
		catch(LoginServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			
			return loginStatus;
		}
	}

}
