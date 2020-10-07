package com.lti.service;



import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.PremiumAnswer;

@Service
@Transactional
public class CalculatePremiumService {
	
	private int age;
	private double depreciatonRate;
	private double idv;
	private double estimatedValue;
	
	
	public PremiumAnswer calculate(int vehiclePrice,LocalDate purchaseDate,double premiumRate,int planYear) {
		
		depreciatonRate=11;
		age=LocalDate.now().getYear()-purchaseDate.getYear();
		
		if(age>7) {
			if(vehiclePrice==50000) 
				idv=5000;
			else
				idv=25000;
				
		}
		else {
			idv=vehiclePrice-(vehiclePrice*age*depreciatonRate)/100; 
		}
		
		if(vehiclePrice==50000) 
			estimatedValue=(idv*premiumRate*planYear)/100;
		else
			estimatedValue=(idv*premiumRate*0.5*planYear)/100;
		
		
		PremiumAnswer premiumAnswer= new PremiumAnswer();
		premiumAnswer.setEstimatedValue(estimatedValue);
		premiumAnswer.setIdv(idv);
		
		return premiumAnswer;
	}

}
