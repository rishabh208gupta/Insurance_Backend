package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "vehicle_seq")
	@SequenceGenerator(name="vehicle_seq", sequenceName ="vehicle_seq" , allocationSize = 1)
	@Column(name="vehicle_id")
	private int vehicleId;
	@Column(name="v_type")
	private String vehicleType;
	private String manufacturer;
	private String model;
	@Column(name="dl_no")
	private long dlNo;
	@Column(name="purchase_date")
	private LocalDate purchaseDate;
	@Column(name="registration_no")
	private int registrationNo;
	@Column(name="engine_no")
	private long engineNo;
	@Column(name="chasis_no")
	private String chasisNo;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToOne(mappedBy="vehicle")
	private NewPolicy newPolicy;
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int id) {
		this.vehicleId = id;
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
	public long getDlNo() {
		return dlNo;
	}
	public void setDlNo(long dlNo) {
		this.dlNo = dlNo;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}
	public NewPolicy getNewPolicy() {
		return newPolicy;
	}
	public void setNewPolicy(NewPolicy newPolicy) {
		this.newPolicy = newPolicy;
	}
	
	
	
}
