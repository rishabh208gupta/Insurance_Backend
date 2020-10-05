package com.lti.dao;

public interface OtpDao {
	
	boolean isEmailPresent(String email);

	boolean isOtpByEmailNotPresent(String email);

	void addNewOtp(String email, String otp);

	void removeOtpByEmailId(String email);

	String fetchOtpByEmail(String email);

}