package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.lti.dao.LoginDao;
import com.lti.entity.Customer;
import com.lti.exception.LoginServiceException;

public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	
	public Customer login(String email,String password) {
		try {
			if(!loginDao.isCustomerPresent(email))
				throw new LoginServiceException("customer not registered");
				int id=loginDao.findByEmailAndPassword(email, password);
			return loginDao.findById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new LoginServiceException("Invalid email,password");
		}
	}

}
