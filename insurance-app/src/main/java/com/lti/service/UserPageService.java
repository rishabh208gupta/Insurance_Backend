package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.UserPageDao;
import com.lti.dto.CheckClaim;
import com.lti.dto.CheckPayment;
import com.lti.entity.Claim;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Vehicle;

@Service
public class UserPageService {

	@Autowired
	private UserPageDao userPageDao;
	
	public List<CheckClaim[]> fetchClaimForPolicy(int customerId){
		return userPageDao.fetchClaimForPolicy(customerId);
	}
	
	public List<CheckPayment[]> fetchPaymentForPolicy(int customerId){
		return userPageDao.fetchPaymentForPolicy(customerId);
	}
	
	public Vehicle fetchUserVehicleDetails(int vehicleId) {
		return userPageDao.fetchById(Vehicle.class, vehicleId);
	}
	
	public NewPolicy fetchUserPolicyDetails(int policyNo) {
		return userPageDao.fetchById(NewPolicy.class, policyNo);
	}
	
	public Claim fetchUserClaimDetails(int claimId) {
		return userPageDao.fetchById(Claim.class, claimId);
	}
	
	public Payment fetchUserPaymentDetails(int paymentId) {
		return userPageDao.fetchById(Payment.class, paymentId);
	}
	public NewPolicy fetchNewPolicyDetails(int policyNo) {
		return userPageDao.fetchPolicyDetails(policyNo);
	}
}
