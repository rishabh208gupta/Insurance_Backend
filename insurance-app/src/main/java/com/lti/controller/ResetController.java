package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PasswordReset;
import com.lti.exception.ResetServiceException;
import com.lti.service.ResetService;
import com.lti.status.ResetStatus;

@RestController
@CrossOrigin
public class ResetController {
	
	@Autowired
	private ResetService resetService;
	
	@PostMapping("/resetpass")
	public ResetStatus resetPassword(@RequestBody PasswordReset passwordReset){
		ResetStatus resetStatus = new ResetStatus();
		try{
			resetStatus.setMessage(resetService.changePasswordService(passwordReset));
			resetStatus.setStatus(100);
			
		}
		catch(ResetServiceException e){
			resetStatus.setMessage(e.getMessage());
			resetStatus.setStatus(101);
		}
		return resetStatus;
	}

}
