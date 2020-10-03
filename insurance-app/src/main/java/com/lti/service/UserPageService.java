package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.UserPageDao;
import com.lti.dto.CheckClaim;

@Service
public class UserPageService {

	@Autowired
	private UserPageDao userPageDao;
	
	public List<CheckClaim[]> fetchClaimForPolicy(int customerId){
		return userPageDao.fetchClaimForPolicy(customerId);
	}
}
