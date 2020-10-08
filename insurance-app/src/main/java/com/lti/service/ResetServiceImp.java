package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.ResetDao;
import com.lti.dto.PasswordReset;
import com.lti.entity.Customer;
import com.lti.exception.ResetServiceException;

@Service
public class ResetServiceImp implements ResetService {

	@Autowired
	private ResetDao resetDao;

	@Override
	public String changePasswordService(PasswordReset passwordReset) throws ResetServiceException {
		Customer customer = resetDao.getCustomerByMailId(passwordReset.getEmailId());
		if (!customer.getPassword().equals(passwordReset.getNewPassword())) {
			customer.setPassword(passwordReset.getNewPassword());
			resetDao.updateCustomer(customer);
			return "Password Changed Successfully";
		} else {
			throw new ResetServiceException("Try another password.");
		}

	}

}
