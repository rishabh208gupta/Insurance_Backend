package com.lti.service;

import com.lti.dto.OtpManagerDto;
import com.lti.exception.OtpServiceException;

public interface OtpService {
	
	String getOtpSentToEmail(String email) throws OtpServiceException;

	String matchAndRemoveOtp(OtpManagerDto otpManagerDto);

}
