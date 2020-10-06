package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminStatus;
import com.lti.dto.Status;
import com.lti.entity.Admin;
import com.lti.entity.Claim;
import com.lti.exception.DashboardServiceException;

import com.lti.service.DashboardService;

@RestController
@CrossOrigin
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@PostMapping(path = "/adminregister")
	public Status register(@RequestBody Admin admin) {
		try {
			int id= dashboardService.registeration(admin);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Registration Successful. AdminId is :"+id);
			return status;

		} 
		catch (DashboardServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping(path="/adminlogin") 
	public AdminStatus login(@RequestBody Admin admin) {
		try {
			Admin a=dashboardService.login(admin.getUsername(), admin.getPassword());
			AdminStatus adminStatus = new AdminStatus();
			adminStatus.setStatus(true);
			adminStatus.setStatusMessage("Login Successful");
			adminStatus.setAdminId(a.getAdminId());
			adminStatus.setUserName(a.getUsername());
			return adminStatus;
			
		}
		catch(DashboardServiceException e) {
			AdminStatus adminStatus = new AdminStatus();
			
			return adminStatus;
		}
	}
	
	@GetMapping(path="/fetchallclaims")
	public List<Claim> fetchClaims(){
		List<Claim> claim=dashboardService.fetchAllClaims();
		return claim;
	}

}
