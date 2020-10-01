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
	public Customer register(Customer customer){
			return entityManager.merge(customer);

		}
		
}
