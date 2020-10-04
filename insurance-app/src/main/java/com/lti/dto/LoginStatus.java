package com.lti.dto;

public class LoginStatus extends Status {
	
	private int customerId;
	private String customerName;
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int l) {
		this.customerId = l;
	}

	

}
