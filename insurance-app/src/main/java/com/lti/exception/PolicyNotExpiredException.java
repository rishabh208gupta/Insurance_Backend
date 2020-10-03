package com.lti.exception;

public class PolicyNotExpiredException extends RuntimeException{

	public PolicyNotExpiredException() {
		super();
	}

	public PolicyNotExpiredException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PolicyNotExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public PolicyNotExpiredException(String message) {
		super(message);
	}

	public PolicyNotExpiredException(Throwable cause) {
		super(cause);
	}
	

}
