package com.lti.service;

import java.time.LocalDate;
import java.time.Period;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.dao.RenewDao;

@Component
@Transactional
public class RenewService {
	@Autowired
	private RenewDao renewDao;
	
	public boolean isPolicyExisting(int policyNo) {
		return renewDao.isPolicyExisting(policyNo);
	}
	
	public boolean hasPolicyExpired(int policyNo,int policyDuration) {
		LocalDate dateOfPayment=renewDao.paymentDate(policyNo);
		Period date= Period.between(dateOfPayment, LocalDate.now());
		if(date.getYears()>policyDuration) {
			return true;
		}else {
			return false;
		}
		
	}

}
