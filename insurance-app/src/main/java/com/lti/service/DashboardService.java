package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.DashboardDao;
import com.lti.entity.Admin;
import com.lti.entity.Claim;
import com.lti.exception.DashboardServiceException;


@Service
@Transactional
public class DashboardService {
	
	@Autowired
	private DashboardDao dashboardDao;
	
	public int registeration(Admin admin) {
		if (!dashboardDao.isAdminPresent(admin.getUsername())) {
			int id= dashboardDao.save(admin);
			return id;
		} 
		else {
			throw new DashboardServiceException("Admin already exist");
		}
	}
	
	public Admin login(String username, String password) {
		try {
			if(!dashboardDao.isAdminPresent(username))
				throw new DashboardServiceException("admin not found");
			int id=dashboardDao.findByUserNameAndPassword(username, password);
			return dashboardDao.findById(id);
				
		}
		catch(EmptyResultDataAccessException e){
			throw new DashboardServiceException("Invalid username/password");
		}
	}
	
	public List<Claim> fetchAllClaims() {
		try {
			List<Claim> list=dashboardDao.fetchAllClaimDetails();
			return list;
		}
		catch(EmptyResultDataAccessException e) {
			throw new DashboardServiceException("No data found!");
		}
	}
}
