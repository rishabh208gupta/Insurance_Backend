package com.lti.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Policy;

@Repository
public class RenewDao extends GenericDao {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean isPolicyExisting(int policyNo) {
		return (long) entityManager.createQuery("select count(n.policyNo) from NewPolicy n where n.policyNo=:policyNo")
				.setParameter("policyNo", policyNo).getSingleResult() == 1 ? true : false;
	}

	public LocalDate paymentDate(int policyNo) {
		return (LocalDate) entityManager
				.createQuery("select max(p.paymentDate) from Payment p join p.newPolicy n where n.policyNo=:policyNo")
				.setParameter("policyNo", policyNo).getSingleResult();
	}

	public Policy fetchPolicy(String policyType, int policyDuration) {
		return (Policy) entityManager.createQuery(
				"select p from Policy p where p.policyType=:policyType and p.policyDuration=:policyDuration")
				.setParameter("policyType", policyType).setParameter("policyDuration", policyDuration)
				.getSingleResult();
	}
	
	public int fetchPolicyDurationByPolicyNo(int policyNo) {
		return (int) entityManager
				.createQuery("select p.policyDuration from NewPolicy n join n.policy p where n.policyNo=:policyNo")
				.setParameter("policyNo", policyNo)
				.getSingleResult();
	}
	
	public double fetchAmountPaid(int policyNo) {
		return 	(double) entityManager
				.createQuery("select p.amount from Payment p join p.newPolicy n where n.policyNo=:policyNo order by p.paymentDate desc")
				.setParameter("policyNo",policyNo)
				 .setMaxResults(1).getSingleResult()  ;
	}
	
	public boolean ifCustomerHasPaid(int policyNo) {
				long isBool= (long)entityManager
				.createQuery("select count(p.paymentId) from Payment p where p.policyNo=:policyNo")
				.setParameter("policyNo", policyNo)
				.getSingleResult();
				if(isBool>0)
					return true;
				else
					return false;
				
	}
}