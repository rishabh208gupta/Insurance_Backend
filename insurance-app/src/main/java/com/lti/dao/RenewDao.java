package com.lti.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Policy;

@Repository
public class RenewDao extends GenericDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean isPolicyExisting(int policyNo) {
		return (long)entityManager
				.createQuery("select count(n.policyNo) from NewPolicy n where n.policyNo=:policyNo")
				.setParameter("policyNo", policyNo)
				.getSingleResult()==1?true:false;			   
	}
	
	public  LocalDate paymentDate(int policyNo) {
		return (LocalDate) entityManager
				.createQuery("select p.paymentDate from Payment p from p.newPolicy n n.policyNo=:policyNo")
				.setParameter("policyNo", policyNo).getSingleResult();
					
		
		
	}

}
