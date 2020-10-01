package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.service.ClaimService;

@RestController
@CrossOrigin
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@GetMapping("/claimpage")
	public List<Object[]> displayOnClaimPage(@RequestParam("customerId") int id){
		return claimService.DisplayOnClaimPage(id);
		
	}
	

}
