package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Customer;

@Component
public class ClaimDao extends GenericDao{
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Object[]> fetchClaimDetails(int customerId) {
		return   entityManager.createQuery("select p.policyId,p.policyType,p.policyDuration,c.customerId,"
				+ "c.name from Customer c join c.vehicles v join v.newPolicy n join n.policy p"
				+ "where c.customerId=:customerId").setParameter("customerId", customerId).getResultList();
		
	}
	
	public List<Customer> fetchCustomer() {
		return  entityManager.createQuery("select c from Customer c").getResultList();
	}

}
