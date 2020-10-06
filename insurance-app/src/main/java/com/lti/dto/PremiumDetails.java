package com.lti.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class PremiumDetails {
	
	
	private int vehiclePrice;
	@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDate purchaseDate;
	private double premiumRate;
	private int planYear;
	
	
	public int getVehiclePrice() {
		return vehiclePrice;
	}
	public void setVehiclePrice(int vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public double getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}
	public int getPlanYear() {
		return planYear;
	}
	public void setPlanYear(int planYear) {
		this.planYear = planYear;
	}

}
