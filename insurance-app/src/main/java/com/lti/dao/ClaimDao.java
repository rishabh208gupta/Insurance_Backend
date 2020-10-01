package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Policy;
import com.lti.exception.ClaimException;

@Component
public class ClaimDao extends GenericDao {

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Policy> fetchClaimDetails(int customerId) {
		return entityManager
				.createQuery("select p from Customer c join c.vehicles v join v.newPolicy n join n.policy p"
				+ " where c.customerId=:customerId").setParameter("customerId", customerId).getResultList();
	}

	public boolean isPolicyPresent(int policyNo) {
		return (long)entityManager
				.createQuery("select count(p.policyNo) from NewPolicy p where p.policyNo=:policyNo")
				.setParameter("policyNo", policyNo)
				.getSingleResult() ==1  ?true:false;
				
				

	}

}
