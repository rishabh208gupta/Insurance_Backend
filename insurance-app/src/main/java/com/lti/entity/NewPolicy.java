package com.lti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="new_policy")
public class NewPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pol_seq")
	@SequenceGenerator(name="pol_seq", sequenceName ="policy_seq" , allocationSize = 1)
	@Column(name="policy_no")
	private int policyNo;
	@ManyToOne
	@JoinColumn(name="policy_id")
	private Policy policy;
	
	@OneToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@OneToMany(mappedBy="newPolicy")
	private List<Claim> claims;
	
	@OneToMany(mappedBy="newPolicy")
	private List<Payment> payments;

	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	
	
}
