package com.lti.exception;

public class RegisterServiceException extends RuntimeException{

	public RegisterServiceException() {
		super();
	}

	public RegisterServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RegisterServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterServiceException(String message) {
		super(message);
	}

	public RegisterServiceException(Throwable cause) {
		super(cause);
	}
	
	

}
