package com.lti.dto;

import com.lti.entity.Payment;

public class PaymentRenew extends Payment{
	private int policyDuration;

	public int getPolicyDuration() {
		return policyDuration;
	}

	public void setPolicyDuration(int policyDuration) {
		this.policyDuration = policyDuration;
	}
	

}
