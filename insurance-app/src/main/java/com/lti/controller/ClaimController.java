package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.StatusClaim;
import com.lti.entity.Claim;
import com.lti.exception.ClaimException;
import com.lti.service.ClaimService;

@RestController
@CrossOrigin
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@GetMapping("/claimpage")
	public List<Object[]> displayOnClaimPage(@RequestParam("customerId") int id) {
		return claimService.displayOnClaimPage(id);

	}

	@GetMapping("/claimstatus")
	public StatusClaim displayOnClaiming(@RequestParam("policyNo") int policyNo,
			@RequestParam("reason") String reason) {
		try {
			if (claimService.isPolicyPresent(policyNo)) {
				Claim claim = claimService.insertClaimDetails(policyNo, reason);
				StatusClaim statusClaim = new StatusClaim();
				statusClaim.setClaimId(claim.getClaimId());
				statusClaim.setStatus(true);
				statusClaim.setStatusMessage("status pending and waiting for approval");
				return statusClaim;
			} else {
				throw new ClaimException("policy doesn't exist wrong policy number");
			}
		} catch (ClaimException e) {
			StatusClaim statusClaim = new StatusClaim();
			statusClaim.setStatus(false);
			statusClaim.setStatusMessage("policy doesnot exist wrong policy number");
			return statusClaim;

		}

	}
}
