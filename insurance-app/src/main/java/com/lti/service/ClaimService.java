package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.ClaimDao;

@Service
public class ClaimService {
	@Autowired
	private ClaimDao claimDao;
	
	public List<Object[]> DisplayOnClaimPage(int customerId){
		return claimDao.fetchClaimDetails(customerId);
	}
	
	

}
