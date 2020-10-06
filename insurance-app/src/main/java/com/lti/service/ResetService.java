package com.lti.service;

import com.lti.dto.PasswordReset;
import com.lti.exception.ResetServiceException;

public interface ResetService {

	String changePasswordService(PasswordReset passwordReset) throws ResetServiceException;

}
