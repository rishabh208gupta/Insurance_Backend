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
@Table(name="payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pay_seq")
	@SequenceGenerator(name="pay_seq", sequenceName ="payment_seq" , allocationSize = 1)
	@Column(name="payment_id")
	private int paymentId;
	@Column(name="payment_mode")
	private String paymentMode;
	@Column(name="payment_date")
	private LocalDate paymentDate;
	private double amount;
	@ManyToOne
	@JoinColumn(name="p_no")
	private NewPolicy newPolicy;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public NewPolicy getNewPolicy() {
		return newPolicy;
	}
	public void setNewPolicy(NewPolicy newPolicy) {
		this.newPolicy = newPolicy;
	}
	
	
}
