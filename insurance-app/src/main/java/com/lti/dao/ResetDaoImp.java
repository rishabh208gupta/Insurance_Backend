package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Customer;

@Repository
public class ResetDaoImp implements ResetDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Customer getCustomerByMailId(String emailId) {
		return (Customer) entityManager.createQuery("select c from Customer as c where c.email= :em")
				.setParameter("em", emailId).getSingleResult();
	}

	@Transactional
	@Override
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);

	}

}
