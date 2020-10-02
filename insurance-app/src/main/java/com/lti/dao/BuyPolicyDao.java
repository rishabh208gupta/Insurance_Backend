package com.lti.dao;

import org.springframework.stereotype.Repository;

import com.lti.entity.NewPolicy;
import com.lti.entity.Policy;

@Repository
public class BuyPolicyDao extends GenericDao{

	public boolean isVehiclePresent(String chasisNo) {
		return (long)entityManager.createQuery("select count(v.vehicleId) from Vehicle v where v.chasisNo = :x").setParameter("x", chasisNo).getSingleResult()==1?true:false;
	}
	
	public Policy getPolicy(NewPolicy newPolicy ) {
		return (Policy)entityManager.createQuery("select p from Policy p where p.policyType=:x and p.policyDuration=:y").setParameter("x", newPolicy.getPolicy().getPolicyType()).setParameter("y", newPolicy.getPolicy().getPolicyDuration()).getSingleResult();
	}
	
	
	
}
