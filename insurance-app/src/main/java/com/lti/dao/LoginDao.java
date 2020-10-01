package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lti.entity.Customer;

public class LoginDao extends GenericDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	
	public Customer findById(int customerId) {
		return entityManager.find(Customer.class, customerId);
	}
	
	
	public int findByEmailAndPassword(String email,String password) {
		return (Integer)entityManager.createQuery("select c.customerId from Customer c where c.email=:em and c.password=:pw").setParameter("em", email).setParameter("pw", password).getSingleResult();
	}
	
	
	
	public boolean isCustomerPresent(String email) {
		return (Long)entityManager.createQuery("select count(c.customerId) from Customer c where c.email=:em ").setParameter("em", email).getSingleResult()==1? true:false;
	}

}
