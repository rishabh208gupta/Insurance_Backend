package com.lti.dto;

import com.lti.entity.Claim;

public class AdminFetchClaimDetails extends Claim {

	int policyNo;

	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}
	
}
