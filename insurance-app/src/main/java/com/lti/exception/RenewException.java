package com.lti.exception;

public class RenewException extends RuntimeException {

	public RenewException() {
		super();
	}

	public RenewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RenewException(String message, Throwable cause) {
		super(message, cause);
	}

	public RenewException(String message) {
		super(message);
	}

	public RenewException(Throwable cause) {
		super(cause);
	}
	

}
