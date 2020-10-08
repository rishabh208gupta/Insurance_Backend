package com.lti.dto;

import java.time.LocalDate;

import com.lti.entity.NewPolicy;

public class VehiclePolicyDetails extends NewPolicy {
	private int vehicleId;
	private String vehicleType;
	private String manufacturer;
	private String model;
	private long dlNo;
	private LocalDate purchaseDate;
	private int registrationNo;
	private long engineNo;
	private String chasisNo;
	private String policyType;
	private int policyDuration;

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public long getDlNo() {
		return dlNo;
	}

	public void setDlNo(long dlNo) {
		this.dlNo = dlNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	public long getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(long engineNo) {
		this.engineNo = engineNo;
	}

	public String getChasisNo() {
		return chasisNo;
	}

	public void setChasisNo(String chasisNo) {
		this.chasisNo = chasisNo;
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

}
