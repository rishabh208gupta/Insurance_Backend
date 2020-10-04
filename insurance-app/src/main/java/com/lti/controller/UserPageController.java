package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CheckClaim;
import com.lti.dto.UserClaim;
import com.lti.dto.UserPolicy;
import com.lti.dto.UserVehicle;
import com.lti.entity.Claim;
import com.lti.entity.NewPolicy;
import com.lti.entity.Policy;
import com.lti.entity.Vehicle;
import com.lti.service.UserPageService;

@RestController
@CrossOrigin
public class UserPageController {

	@Autowired
	private UserPageService userPageService;
	
	@GetMapping("/fetch-claim-policy")
	public List<CheckClaim[]> fetchClaimForPolicy(@RequestParam("customerId") int customerId){
		return userPageService.fetchClaimForPolicy(customerId);
	}
	
	@GetMapping("/fetch-user-vehicle-details")
	public UserVehicle fetchUserVehicleDetails(@RequestParam("vehicleId") int vehicleId) {
		Vehicle vehicle= userPageService.fetchUserVehicleDetails(vehicleId);
		UserVehicle userVehicle = new UserVehicle();
		userVehicle.setVehicleId(vehicle.getVehicleId());
		userVehicle.setVehicleType(vehicle.getVehicleType());
		userVehicle.setChasisNo(vehicle.getChasisNo());
		return userVehicle;
	}
	
	@GetMapping("/fetch-user-policy-details")
	public UserPolicy fetchUserPolicyDetails(@RequestParam("policyNo") int policyNo) {
		NewPolicy newPolicy = userPageService.fetchUserPolicyDetails(policyNo);
		UserPolicy userPolicy = new UserPolicy();
		userPolicy.setPolicyNo(newPolicy.getPolicyNo());
		userPolicy.setPolicyType(newPolicy.getPolicy().getPolicyType());
		userPolicy.setPolicyDuration(newPolicy.getPolicy().getPolicyDuration());
		return userPolicy;
		
	}
	
	@GetMapping("/fetch-user-claim-details")
	public UserClaim fetchUserClaimDetails(@RequestParam("claimId") int claimId) {
		Claim claim = userPageService.fetchUserClaimDetails(claimId);
		UserClaim userClaim = new UserClaim();
		if(claim.getStatus()=="approved") {
			userClaim.setClaimed(true);
			userClaim.setClaimAmount(claim.getAdminAmount());
			userClaim.setClaimId(claimId);
			return userClaim;
		}
		else {
			userClaim.setClaimed(false);
			userClaim.setClaimId(claimId);
			return userClaim;
		}
	}
}
