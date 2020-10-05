package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ForgotensMail;
import com.lti.dto.OtpManagerDto;
import com.lti.exception.OtpServiceException;
import com.lti.service.OtpService;
import com.lti.status.CustomerStatus;

@RestController
@CrossOrigin
public class OtpController {
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private MailSender mailSender;
	
	@PostMapping("/emailotp")
	public CustomerStatus sendEmailOtp(@RequestBody ForgotensMail forgotensMail) {
	CustomerStatus customerStatus = new CustomerStatus();
		
		try {
			String otp = otpService.getOtpSentToEmail(forgotensMail.getMailid());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("abc_12341983@outlook.com");
			message.setTo(forgotensMail.getMailid());
			message.setSubject("no reply");
			message.setText("You have recieved this mail for verification. Your verfication OTP is : "+otp);
			mailSender.send(message);
			
			customerStatus.setStatus(100);
			customerStatus.setMessage("OTP mail send successfully");
			return customerStatus;
		}
		catch(OtpServiceException e) {
			customerStatus.setStatus(101);
			customerStatus.setMessage(e.getMessage());
			return customerStatus;
		}
		
	}
	
	@PostMapping("/verifyotp")
	public CustomerStatus verifyEmailOtp(@RequestBody OtpManagerDto otpManagerDto) {
		CustomerStatus customerStatus = new CustomerStatus();
		try {
			
			String message = otpService.matchAndRemoveOtp(otpManagerDto);
			customerStatus.setStatus(100);
			customerStatus.setMessage(message);
			return customerStatus;
			
		}
		catch(OtpServiceException e) {
			customerStatus.setStatus(101);
			customerStatus.setMessage(e.getMessage());
			return customerStatus;
		}		
	}

}
