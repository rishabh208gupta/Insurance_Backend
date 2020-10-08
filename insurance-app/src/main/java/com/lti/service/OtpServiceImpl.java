package com.lti.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.OtpDao;
import com.lti.exception.OtpServiceException;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpDao otpDao;

	@Override
	public String getOtpSentToEmail(String email) throws OtpServiceException {
		if (otpDao.isEmailPresent(email)) {
			String numbers = "1234567890";
			Random random = new Random();
			String otp = "";

			for (int i = 0; i < 6; i++) {
				otp = otp + numbers.charAt(random.nextInt(numbers.length()));
			}
			return otp;

		} else {
			throw new OtpServiceException("User with this email id is not present");
		}
	}

}