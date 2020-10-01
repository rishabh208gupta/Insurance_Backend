package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PaymentStatus;
import com.lti.dto.PolicyRegistrationStatus;
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
			policyRegistrationStatus.setPolicyId(newPolicy.getPolicyNo());
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
	
	@PostMapping("/make-payment")
	public PaymentStatus makePayment(@RequestBody Payment payment) {
		try {
			payment = buyPolicyService.makePayment(payment);
			PaymentStatus paymentStatus = new PaymentStatus();
			paymentStatus.setStatus(true);
			paymentStatus.setStatusMessage("payment successful");
			return paymentStatus;
		}
		catch(Exception e) {
			PaymentStatus paymentStatus = new PaymentStatus();
			paymentStatus.setStatus(false);
			paymentStatus.setStatusMessage(e.getMessage());
			return paymentStatus;
		}
	}
}
