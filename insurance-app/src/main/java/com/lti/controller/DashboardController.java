package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminFetchClaimDetails;
import com.lti.dto.AdminStatus;
import com.lti.dto.Status;
import com.lti.entity.Admin;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
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
	public List<AdminFetchClaimDetails> fetchClaims(){
		try {
			List<Claim> claimslist=dashboardService.fetchAllClaims();
			List<AdminFetchClaimDetails> claims=new ArrayList<>();
			for(int i=0;i<claimslist.size();i++) {
				AdminFetchClaimDetails claim=new AdminFetchClaimDetails();
				claim.setClaimId(claimslist.get(i).getClaimId());
				claim.setReason(claimslist.get(i).getReason());
				claim.setStatus(claimslist.get(i).getStatus());
				claim.setDateApplied(claimslist.get(i).getDateApplied());
				claim.setAdminAmount(claimslist.get(i).getAdminAmount());
				claim.setPolicyNo(claimslist.get(i).getNewPolicy().getPolicyNo());
				claims.add(claim);
			}
			return claims;
		}
		catch(DashboardServiceException e) {
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	/*
	@GetMapping(path="/userdetails")
	public Customer fetchUserDetails(@RequestParam("claimId") int claimId){
		try {
			return dashboardService.fetchCustomerByClaimId(claimId);
		}
		catch(DashboardServiceException e) {
			
			
		}
	}
	*/

}
