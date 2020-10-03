package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.dto.ForgotDto;
import com.lti.dto.ForgotPasswordStatus;
import com.lti.dto.OtpDto;
import com.lti.dto.ResetDto;
import com.lti.dto.StatusDto;
import com.lti.dto.StatusDto.StatusType;
import com.lti.exception.CustomerServiceException;
import com.lti.service.EmailService;
import com.lti.service.LoginService;

public class PasswordController {
	
}
