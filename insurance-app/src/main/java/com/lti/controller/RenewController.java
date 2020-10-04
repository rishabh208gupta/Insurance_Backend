package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PaymentRenew;
import com.lti.dto.PaymentStatus;
import com.lti.dto.PolicyRegistrationStatus;
import com.lti.dto.VehicleRegistrationStatus;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.exception.PolicyNotExpiredException;
import com.lti.exception.RenewException;
import com.lti.service.BuyPolicyService;
import com.lti.service.RenewService;

@CrossOrigin
@RestController
public class RenewController {

	@Autowired
	private RenewService renewService;

	@Autowired
	private BuyPolicyService buyPolicyService;

	@GetMapping("/renew-policy")
	public PolicyRegistrationStatus renewPolicy(@RequestParam("policyNo") int policyNo,
			@RequestParam("policyDuration") int policyDuration) {
		try {
			if (!renewService.isPolicyExisting(policyNo))
				throw new RenewException("there is no policy with this policy number");
			if (!renewService.hasPolicyExpired(policyNo))
				throw new PolicyNotExpiredException(
						"policy has not expired yet , one cannot renew after expiry of policy ");
			PolicyRegistrationStatus policyRegistrationStatus = new PolicyRegistrationStatus();
			policyRegistrationStatus.setPolicyNo(policyNo);
			policyRegistrationStatus.setStatus(true);
			policyRegistrationStatus.setStatusMessage("proceed for payment");
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
					.setStatusMessage("policy has not expired yet , one can renew after expiry of policy");
			return policyRegistrationStatus;

		}
	}

	@PostMapping("/make-payment-renew")
	public PaymentStatus makePayment(@RequestBody PaymentRenew renewPayment) {
		try {
			if(renewPayment.isStatusConfirmation()==false)
				throw new RenewException("payment unsuccessful");
			Payment successPayment=renewService.makePayment(renewPayment);
			NewPolicy renewedPolicy = renewService.renewPolicy(renewPayment.getNewPolicy().getPolicyNo(),
					renewPayment.getPolicyDuration());
			PaymentStatus paymentStatus = new PaymentStatus();
			paymentStatus.setStatus(true);
			paymentStatus.setStatusMessage("payment successful");
			return paymentStatus;
		} catch (RenewException e) {
			PaymentStatus paymentStatus = new PaymentStatus();
			paymentStatus.setStatus(false);
			paymentStatus.setStatusMessage("could not renew the policy");
			return paymentStatus;
		}
	}

}
