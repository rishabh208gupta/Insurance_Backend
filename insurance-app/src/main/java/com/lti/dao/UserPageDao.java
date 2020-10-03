package com.lti.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.dto.CheckClaim;


@Repository
public class UserPageDao extends GenericDao{

	public List<CheckClaim[]> fetchClaimForPolicy(int customerId){
		return entityManager.createQuery("select v.vehicleId, np.policyNo,c.claimId from Claim c full join c.newPolicy np full join np.vehicle v full join v.customer cus where cus.customerId = :x").setParameter("x", customerId).getResultList();
	}
	
	
}
