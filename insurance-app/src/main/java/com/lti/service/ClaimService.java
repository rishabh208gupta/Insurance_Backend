package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.ClaimDao;
import com.lti.entity.Claim;
import com.lti.entity.NewPolicy;
import com.lti.exception.ClaimException;

@Service
@Transactional
public class ClaimService {
	@Autowired
	private ClaimDao claimDao;
	
	public List<Object[]> displayOnClaimPage(int customerId){
		try {
		return claimDao.fetchClaimDetails(customerId);
		}
		catch(ClaimException e) {
			throw new ClaimException("not able to select data for the customer");
		}
	}
	
	public Claim insertClaimDetails(int policyNo,String reason) {
		try {
			if(claimDao.isPolicyPresent(policyNo)) {
			Claim claim =new Claim();
			claim.setDateApplied(LocalDate.now());
			claim.setStatus("pending");
			claim.setReason(reason);
			NewPolicy newPolicy=claimDao.fetchById(NewPolicy.class, policyNo);
			claim.setNewPolicy(newPolicy);
			return claimDao.save(claim);	
			}
			else {
				throw new ClaimException("policy number does not exist");
			}
		}
		catch(ClaimException e) {
			throw new ClaimException("claim details not inserted");
		}
		
	}
	
	

}
