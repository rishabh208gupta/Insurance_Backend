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
	
	
}
