package com.lti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy {

	@Id
	@Column(name="policy_id")
	private int policyId;
	@Column(name="policy_type")
	private String policyType;
	@Column(name="policy_duration")
	private int policyDuration;
	
	@OneToMany(mappedBy="policy")
	private List<NewPolicy> newPolicies;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public int getPolicyDuration() {
		return policyDuration;
	}

	public void setPolicyDuration(int policyDuration) {
		this.policyDuration = policyDuration;
	}

	public List<NewPolicy> getNewPolicies() {
		return newPolicies;
	}

	public void setNewPolicies(List<NewPolicy> newPolicies) {
		this.newPolicies = newPolicies;
	}
	
	
	
}
