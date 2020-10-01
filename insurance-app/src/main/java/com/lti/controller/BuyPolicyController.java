package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.VehicleRegistrationStatus;
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
}
