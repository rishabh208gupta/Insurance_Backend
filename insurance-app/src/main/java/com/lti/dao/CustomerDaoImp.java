package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;

@Repository
public class CustomerDaoImp implements CustomerDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public Customer register(Customer customer) {
		entityManager.merge(customer);
		return customer;

	}

	@Override
	public Customer findById(int id) {
		return entityManager.find(Customer.class, id);

	}

	@Override
	public Customer findByEmail(String email) {
		return entityManager.find(Customer.class, email);
	}

	@Override
	public boolean isCustomerPresent(String email) {
		return (Long) entityManager.createQuery("select count(c.id) from Customer c where c.email = :em")
				.setParameter("em", email).getSingleResult() == 1 ? true : false;
	}

}
