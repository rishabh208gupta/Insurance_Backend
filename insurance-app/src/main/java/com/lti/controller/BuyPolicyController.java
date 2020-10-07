package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Bill;
import com.lti.dto.PaymentStatus;
import com.lti.dto.PolicyRegistrationStatus;
import com.lti.dto.PremiumStatus;
import com.lti.dto.VehicleRegistrationStatus;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Vehicle;
import com.lti.exception.BuyPolicyException;
import com.lti.service.BuyPolicyService;

@RestController
@CrossOrigin
public class BuyPolicyController {

	@Autowired
	private BuyPolicyService buyPolicyService;
	
	@PostMapping("/register-vehicle")
	public VehicleRegistrationStatus registerVehicle(@RequestBody Vehicle vehicle) {
		try {
			vehicle=buyPolicyService.registerVehicle(vehicle);
			VehicleRegistrationStatus vehicleRegistrationStatus = new VehicleRegistrationStatus();
			vehicleRegistrationStatus.setStatus(true);
			vehicleRegistrationStatus.setStatusMessage("vehicle registration successful");
			vehicleRegistrationStatus.setVehicleId(vehicle.getVehicleId());
			return vehicleRegistrationStatus;
		}
		catch(BuyPolicyException e) {
			VehicleRegistrationStatus vehicleRegistrationStatus = new VehicleRegistrationStatus();
			vehicleRegistrationStatus.setStatus(false);
			vehicleRegistrationStatus.setStatusMessage(e.getMessage());
			return vehicleRegistrationStatus;
		}
	}
	
	@PostMapping("/register-policy")
	public PolicyRegistrationStatus registerPolicy(@RequestBody NewPolicy newPolicy) {
		try {
			newPolicy = buyPolicyService.registerPolicy(newPolicy);
			PolicyRegistrationStatus policyRegistrationStatus = new PolicyRegistrationStatus();
			policyRegistrationStatus.setPolicyNo(newPolicy.getPolicyNo());
			policyRegistrationStatus.setStatus(true);
			policyRegistrationStatus.setStatusMessage("policy registered successfully");
			return policyRegistrationStatus;
		}
		catch(Exception e) {
			PolicyRegistrationStatus policyRegistrationStatus = new PolicyRegistrationStatus();
			policyRegistrationStatus.setStatus(false);
			policyRegistrationStatus.setStatusMessage(e.getMessage());
			return policyRegistrationStatus;
		}
		
	}
	
	@GetMapping("/bill-details")
	public Bill getBillDetails(@RequestParam("policyNo") int policyNo) {
		try {
			NewPolicy newPolicy = buyPolicyService.getBillDetails(policyNo);
			Bill bill = new Bill();
			bill.setCustomerName(newPolicy.getVehicle().getCustomer().getName());
			bill.setPhoneNo(newPolicy.getVehicle().getCustomer().getPhoneNo());
			bill.setVehicleType(newPolicy.getVehicle().getVehicleType());
			bill.setManufacturer(newPolicy.getVehicle().getManufacturer());
			bill.setChasisNo(newPolicy.getVehicle().getChasisNo());
			bill.setPolicyType(newPolicy.getPolicy().getPolicyType());
			bill.setPolicyDuration(newPolicy.getPolicy().getPolicyDuration());
			bill.setAmount(1000);
			return bill;
		}
		catch(Exception e) {
			return null;
		}
		
		
	}
	
	@PostMapping("/make-payment")
	public PaymentStatus makePayment(@RequestBody Payment payment) {
		try {
			payment.setPaymentDate(LocalDate.now());
			payment = buyPolicyService.makePayment(payment);
			PaymentStatus paymentStatus = new PaymentStatus();
			paymentStatus.setStatus(true);
			paymentStatus.setStatusMessage("payment successful");
			paymentStatus.setPolicyNo(payment.getNewPolicy().getPolicyNo());
			return paymentStatus;
		}
		catch(Exception e) {
			PaymentStatus paymentStatus = new PaymentStatus();
			paymentStatus.setStatus(false);
			paymentStatus.setStatusMessage(e.getMessage());
			return paymentStatus;
		}
	}
	
	@GetMapping("/buy-policy-calculate-premium")
	public PremiumStatus calculatePremium(@RequestParam("vehicleId") int vehicleId) {
		try {
			PremiumStatus premiumStatus = buyPolicyService.calculatePremium(vehicleId);
			premiumStatus.setStatus(true);
			premiumStatus.setStatusMessage("calculated premium");
			return premiumStatus;
		}
		catch(BuyPolicyException e) {
			PremiumStatus premiumStatus = new PremiumStatus();
			premiumStatus.setStatus(false);
			premiumStatus.setStatusMessage(e.getMessage());
			return premiumStatus;
		}
	}
}
