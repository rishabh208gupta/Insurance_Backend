package com.lti.dao;

import org.springframework.stereotype.Repository;

import com.lti.entity.Vehicle;

@Repository
public class BuyPolicyDao extends GenericDao{

	public boolean isVehiclePresent(String chasisNo) {
		return (long)entityManager.createQuery("select count(v.vehicleId) from Vehicle v where v.chasisNo = :x").setParameter("x", chasisNo).getSingleResult()==1?true:false;
	}
	
}
