package com.lti.dto;

public class UserClaim {

	private int claimId;
	private boolean claimed;
	private String adminMessage;
	private double claimAmount;
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public boolean isClaimed() {
		return claimed;
	}
	public void setClaimed(boolean claimStatus) {
		this.claimed = claimStatus;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getAdminMessage() {
		return adminMessage;
	}
	public void setAdminMessage(String adminMessage) {
		this.adminMessage = adminMessage;
	}
	
	
}
