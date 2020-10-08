package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class OtpDaoImp implements OtpDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean isEmailPresent(String mailid) {
		return (Long) entityManager.createQuery("select count(c.customerId) from Customer as c where c.email= :em")
				.setParameter("em", mailid).getSingleResult() == 1 ? true : false;

	}

}
