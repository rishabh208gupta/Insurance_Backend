package com.lti.exception;

public class OtpServiceException extends RuntimeException {

	public OtpServiceException() {
		super();
	}

	public OtpServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public OtpServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public OtpServiceException(String arg0) {
		super(arg0);
	}

	public OtpServiceException(Throwable arg0) {
		super(arg0);
	}

}
