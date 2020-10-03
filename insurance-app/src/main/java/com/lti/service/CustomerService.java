package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lti.dao.CustomerDao;
import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;

public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	private static String otpStr;
	public boolean preResetCheck(String email) {
        Customer c= customerDao.findByEmail(email);
        if(c !=null) 
        {
            otpStr = SendOtp.generateOtp();
            System.out.println("Generated OTP= " + otpStr);
            SendOtp.sendSMS(email, c.getPhoneNo());
            return true;
        }
        
        throw new CustomerServiceException("Enter Valid Email ID");
        
    }

	public boolean validateOtp(String otp) {
        if(otp.equals(otpStr)) {
            return true;
        }
        return false;
    }
}
