package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.PremiumAnswer;
import com.lti.dto.PremiumDetails;
import com.lti.entity.Customer;
import com.lti.exception.DashboardServiceException;
import com.lti.exception.LoginServiceException;
import com.lti.service.CalculatePremiumService;

@RestController
@CrossOrigin
public class CalculatePremiumController {
	
	@Autowired
	private CalculatePremiumService calculatePremiumService;
	
	@PostMapping(path="/premium") 
	public PremiumAnswer calculate(@RequestBody PremiumDetails premiumDetails) {
		try {
			PremiumAnswer premiumAnswer=calculatePremiumService.calculate(premiumDetails.getVehiclePrice(),premiumDetails.getPurchaseDate(),premiumDetails.getPremiumRate(),premiumDetails.getPlanYear());
			return premiumAnswer;
			
		}
		catch(DashboardServiceException e) {
			PremiumAnswer premiumAnswer=new PremiumAnswer();
			return premiumAnswer;
		}
	}
	
	
	

}
