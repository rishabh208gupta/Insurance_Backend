package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cl_seq")
	@SequenceGenerator(name="cl_seq", sequenceName ="claim_seq" , allocationSize = 1)
	@Column(name="claim_id")
	private int claimId;
	private String reason;
	private String status;
	@Column(name="date_applied")
	private LocalDate dateApplied;
	@Column(name="admin_amount")
	private double adminAmount;
	@ManyToOne
	@JoinColumn(name="p_no")
	private NewPolicy newPolicy;
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(LocalDate dateApplied) {
		this.dateApplied = dateApplied;
	}
	public double getAdminAmount() {
		return adminAmount;
	}
	public void setAdminAmount(double adminAmount) {
		this.adminAmount = adminAmount;
	}
	public NewPolicy getNewPolicy() {
		return newPolicy;
	}
	public void setNewPolicy(NewPolicy newPolicy) {
		this.newPolicy = newPolicy;
	}
	
	
}
