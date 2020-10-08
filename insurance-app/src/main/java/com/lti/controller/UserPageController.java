package com.lti.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CheckClaim;
import com.lti.dto.CheckPayment;
import com.lti.dto.UserClaim;
import com.lti.dto.UserPayment;
import com.lti.dto.UserPolicy;
import com.lti.dto.UserVehicle;
import com.lti.dto.VehiclePolicyDetails;
import com.lti.entity.Claim;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
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
	
	@GetMapping("/fetch-payment-policy")
	public List<CheckPayment[]> fetchPaymentForPolicy(@RequestParam("customerId") int customerId){
		return userPageService.fetchPaymentForPolicy(customerId);
	}
	
	@GetMapping("/fetch-user-vehicle-details")
	public UserVehicle fetchUserVehicleDetails(@RequestParam("vehicleId") int vehicleId) {
		Vehicle vehicle= userPageService.fetchUserVehicleDetails(vehicleId);
		UserVehicle userVehicle = new UserVehicle();
		userVehicle.setVehicleId(vehicle.getVehicleId());
		userVehicle.setVehicleType(vehicle.getVehicleType());
		userVehicle.setChasisNo(vehicle.getChasisNo());
		userVehicle.setManufacturer(vehicle.getManufacturer());
		userVehicle.setPurchaseDate(vehicle.getPurchaseDate());
		userVehicle.setRegistrationNo(vehicle.getRegistrationNo());
		
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
		System.out.println(claim.getStatus());
		boolean b;
		if(claim.getStatus().equalsIgnoreCase("approved")) {
			b=true;
		}
		else {
			b=false;
		}
		if(b) {
			userClaim.setClaimed(true);
			System.out.println("true");
			userClaim.setClaimAmount(claim.getAdminAmount());
			userClaim.setClaimId(claimId);
			userClaim.setAdminMessage("claim approved for amount "+claim.getAdminAmount());
			return userClaim;
		}
		else {
			if(claim.getStatus().equalsIgnoreCase("reject")) {
				userClaim.setClaimed(false);
				userClaim.setClaimId(claimId);
				userClaim.setAdminMessage("claim rejected");
				return userClaim;
			}
			else {
				userClaim.setClaimed(false);
				userClaim.setClaimId(claimId);
				userClaim.setAdminMessage("approval pending...");
				return userClaim;
			}
			
		}
	}
	
	@GetMapping("/fetch-user-payment-details")
	public UserPayment fetchUserPaymentDetails(@RequestParam("paymentId") int paymentId) {
		Payment payment = userPageService.fetchUserPaymentDetails(paymentId);
		UserPayment userPayment = new UserPayment();
		userPayment.setPaymentId(payment.getPaymentId());
		userPayment.setPaid(true);
		Period period = Period.between(payment.getPaymentDate(), LocalDate.now());
		userPayment.setAge(period.getDays());
		return userPayment;
	}
	
	@GetMapping("fetch-vehicle-policy-details")
	public VehiclePolicyDetails fetchVehiclePolicyDetails(@RequestParam("policyNo") int policyNo) {
		NewPolicy newPolicy = userPageService.fetchNewPolicyDetails(policyNo);
		VehiclePolicyDetails vehiclePolicyDetails=new VehiclePolicyDetails();
		vehiclePolicyDetails.setVehicleId(newPolicy.getVehicle().getVehicleId());
		vehiclePolicyDetails.setVehicleType(newPolicy.getVehicle().getVehicleType());
		vehiclePolicyDetails.setManufacturer(newPolicy.getVehicle().getManufacturer());
		vehiclePolicyDetails.setModel(newPolicy.getVehicle().getModel());
		vehiclePolicyDetails.setDlNo(newPolicy.getVehicle().getDlNo());
		vehiclePolicyDetails.setPurchaseDate(newPolicy.getVehicle().getPurchaseDate());
		vehiclePolicyDetails.setRegistrationNo(newPolicy.getVehicle().getRegistrationNo());
		vehiclePolicyDetails.setEngineNo(newPolicy.getVehicle().getEngineNo());
		vehiclePolicyDetails.setChasisNo(newPolicy.getVehicle().getChasisNo());
		vehiclePolicyDetails.setPolicyType(newPolicy.getPolicy().getPolicyType());
		vehiclePolicyDetails.setPolicyDuration(newPolicy.getPolicy().getPolicyDuration());
		return vehiclePolicyDetails;
		
		
		
	}
}
