package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;

@Repository
public class CustomerDaoImp implements CustomerDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public int register(Customer customer){
			 entityManager.merge(customer);
			 return customer.getCustomerId();

		}
	
	@Override
	public Customer findById(int id) {
		return entityManager.find(Customer.class, id);
		
	}
	
	@Override
	public int findByEmailAndPassword(String email, String password) {
		return (Integer) entityManager
				.createQuery("select c.id from Customer c where c.email = :em and c.password = :pw")
				.setParameter("em", email).setParameter("pw", password).getSingleResult();
	}
	
	@Override
	public boolean isCustomerPresent(String email) {
		return (Long) entityManager.createQuery("select count(c.id) from Customer c where c.email = :em")
				.setParameter("em", email).getSingleResult() == 1 ? true : false;
	}
		
}
