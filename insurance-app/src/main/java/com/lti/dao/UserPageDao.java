package com.lti.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.dto.CheckClaim;
import com.lti.dto.CheckPayment;


@Repository
public class UserPageDao extends GenericDao{

	public List<CheckClaim[]> fetchClaimForPolicy(int customerId){
		return entityManager.createQuery("select v.vehicleId, np.policyNo,c.claimId from Claim c full join c.newPolicy np full join np.vehicle v full join v.customer cus where cus.customerId = :x").setParameter("x", customerId).getResultList();
	}
	
	public List<CheckPayment[]> fetchPaymentForPolicy(int customerId){
		return entityManager.createQuery("select v.vehicleId, np.policyNo, p.paymentId from Payment p full join p.newPolicy np full join np.vehicle v full join v.customer cus where cus.customerId = :x").setParameter("x", customerId).getResultList();
	}
	
	
}
