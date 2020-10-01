package com.lti.exception;

public class ClaimException extends RuntimeException{

	public ClaimException() {
		super();
	}

	public ClaimException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClaimException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClaimException(String message) {
		super(message);
	}

	public ClaimException(Throwable cause) {
		super(cause);
	}
	

}
