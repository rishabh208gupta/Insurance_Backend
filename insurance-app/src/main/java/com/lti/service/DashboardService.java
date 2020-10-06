package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.DashboardDao;
import com.lti.dto.AdminApproval;
import com.lti.entity.Admin;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
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
	
	public Customer fetchCustomerByClaimId(int claimId) {
		try {
			return dashboardDao.fetchCustomerDetailsfromClaimId(claimId);
		}
		catch(EmptyResultDataAccessException e) {
			throw new DashboardServiceException("No data found!");
		}
	}
	
	public void adminApproval(AdminApproval adminApproval) {
		try {
		Claim claim= dashboardDao.fetchById(Claim.class, adminApproval.getClaimId());
		claim.setAdminAmount(adminApproval.getAmount());
		claim.setStatus(adminApproval.getStatus());
		dashboardDao.save(claim);
		}
		catch(DashboardServiceException e) {
			throw new DashboardServiceException("data not updated");
		}
		
	}
}
