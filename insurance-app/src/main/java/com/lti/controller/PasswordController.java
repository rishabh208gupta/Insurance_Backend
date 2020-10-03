package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.dto.ForgotDto;
import com.lti.dto.ForgotPasswordStatus;
import com.lti.service.EmailService;
import com.lti.service.LoginService;

public class PasswordController {
	
	@Autowired
	private LoginService userService;

	@Autowired
	private EmailService emailService;
	

}
