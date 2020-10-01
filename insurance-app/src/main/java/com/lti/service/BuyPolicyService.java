package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.BuyPolicyDao;
import com.lti.entity.Vehicle;
import com.lti.exception.BuyPolicyException;

@Service
public class BuyPolicyService {

	@Autowired
	private BuyPolicyDao buyPolicyDao;
	
	@Transactional
	public Vehicle registerVehicle(Vehicle vehicle) {
		if(!buyPolicyDao.isVehiclePresent(vehicle.getChasisNo())) {
			return buyPolicyDao.save(vehicle);
		}
		else {
			throw new BuyPolicyException("vehicle already registered");
		}
	}
}
