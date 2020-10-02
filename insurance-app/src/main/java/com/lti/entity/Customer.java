package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "user_seq")
	@SequenceGenerator(name = "user_seq" , initialValue = 1001 , allocationSize = 1)
	@Column(name="customer_id")
	private long customerId;
	private String name;
	@Column(name="phone_no")
	private long phoneNo;
	@Column(name="email_id")
	private String email;
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	private String address;
	private String password;
	@OneToMany(mappedBy="customer")
	private List<Vehicle> vehicles;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int id) {
		this.customerId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
	
	
}
