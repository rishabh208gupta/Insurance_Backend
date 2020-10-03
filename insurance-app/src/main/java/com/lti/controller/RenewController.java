package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lti.dto.PolicyRegistrationStatus;
import com.lti.dto.VehicleRegistrationStatus;
import com.lti.entity.NewPolicy;
import com.lti.exception.PolicyNotExpiredException;
import com.lti.exception.RenewException;
import com.lti.service.RenewService;

@CrossOrigin
@RestController
public class RenewController {

	@Autowired
	private RenewService renewService;

	@GetMapping("/renew-policy")
	public PolicyRegistrationStatus renewPolicy(@RequestParam("policyNo") int policyNo,
			@RequestParam("policyDuration") int policyDuration) {
		try {
			if (!renewService.isPolicyExisting(policyNo))
				throw new RenewException("there is no policy with this policy number");
			if (!renewService.hasPolicyExpired(policyNo, policyDuration))
				throw new PolicyNotExpiredException(
						"policy has not expired yet , one cannot renew after expiry of policy ");
			NewPolicy renewedPolicy = renewService.renewPolicy(policyNo, policyDuration);
			PolicyRegistrationStatus policyRegistrationStatus = new PolicyRegistrationStatus();
			policyRegistrationStatus.setPolicyNo(renewedPolicy.getPolicyNo());
			policyRegistrationStatus.setStatus(true);
			policyRegistrationStatus.setStatusMessage("policy registered successfully");
			return policyRegistrationStatus;

		} catch (RenewException e) {
			PolicyRegistrationStatus policyRegistrationStatus = new PolicyRegistrationStatus();
			policyRegistrationStatus.setStatus(false);
			policyRegistrationStatus.setStatusMessage("there is no policy existing to renew");
			return policyRegistrationStatus;

		} catch (PolicyNotExpiredException e) {
			PolicyRegistrationStatus policyRegistrationStatus = new PolicyRegistrationStatus();
			policyRegistrationStatus.setStatus(false);
			policyRegistrationStatus
					.setStatusMessage("policy has not expired yet , one cannot renew after expiry of policy");
			return policyRegistrationStatus;

		}
	}

}
