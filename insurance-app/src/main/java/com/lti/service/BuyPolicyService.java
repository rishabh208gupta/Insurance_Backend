package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.BuyPolicyDao;
import com.lti.entity.Customer;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Vehicle;
import com.lti.exception.BuyPolicyException;

@Service
public class BuyPolicyService {

	@Autowired
	private BuyPolicyDao buyPolicyDao;
	
	@Transactional
	public Vehicle registerVehicle(Vehicle vehicle) {
		if(!buyPolicyDao.isVehiclePresent(vehicle.getChasisNo())) {
			Customer customer = buyPolicyDao.fetchById(Customer.class, vehicle.getCustomer().getCustomerId());
			System.out.println(vehicle.getCustomer().getCustomerId());
			vehicle.setCustomer(customer);
			return buyPolicyDao.save(vehicle);
		}
		else {
			throw new BuyPolicyException("vehicle already registered");
		}
	}
	
	@Transactional
	public NewPolicy registerPolicy(NewPolicy newPolicy) {
		
			int policyId = buyPolicyDao.getPolicy(newPolicy);
			newPolicy.getPolicy().setPolicyId(policyId);
			return buyPolicyDao.save(newPolicy);
		
	}
	
	@Transactional
	public Payment makePayment(Payment payment) {
		return buyPolicyDao.save(payment);
	}
}
