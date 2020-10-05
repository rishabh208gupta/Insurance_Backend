package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.OtpManager;


@Repository
public class OtpDaoImp implements OtpDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	

	@Override
	public boolean isEmailPresent(String mailid) {
		return (Long) entityManager
				.createQuery("select count(c.customerId) from Customer as c where c.email= :em")
				.setParameter("em", mailid)
				.getSingleResult()==1?true:false;
			
	}

	@Override
	public boolean isOtpByEmailNotPresent(String mailid) {
		return (Long) entityManager
				.createQuery("select count(o.id) from OtpManager as o where o.emailId = :ei")
				.setParameter("ei", mailid)
				.getSingleResult()==0?true:false;
	}

	@Transactional
	@Override
	public void addNewOtp(String mailid, String otp) {
		// TODO Auto-generated method stub
		OtpManager otpManager = new OtpManager();
		otpManager.setEmailId(mailid);
		otpManager.setOtp(otp);
			
		entityManager.merge(otpManager);
		
		

	}

	@Transactional
	@Override
	public void removeOtpByEmailId(String mailid) {
		// TODO Auto-generated method stub
		OtpManager otpManager =(OtpManager) entityManager
				.createQuery("select o from OtpManager as o where o.emailId= :ei")
				.setParameter("ei", mailid)
				.getSingleResult();
		
		entityManager.remove(otpManager);

	}

	@Override
	public String fetchOtpByEmail(String email) {
		return (String) entityManager
				.createQuery("select o.otp from OtpManager as o where o.emailId = :ei")
				.setParameter("ei", email)
				.getSingleResult();
	}

}
