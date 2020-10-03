package com.lti.dto;

import com.lti.entity.Payment;

public class PaymentRenew extends Payment{
	private int policyDuration;
	private boolean statusConfirmation;
	
	public boolean isStatusConfirmation() {
		return statusConfirmation;
	}

	public void setStatusConfirmation(boolean statusConfirmation) {
		this.statusConfirmation = statusConfirmation;
	}

	public int getPolicyDuration() {
		return policyDuration;
	}

	public void setPolicyDuration(int policyDuration) {
		this.policyDuration = policyDuration;
	}
	

}
