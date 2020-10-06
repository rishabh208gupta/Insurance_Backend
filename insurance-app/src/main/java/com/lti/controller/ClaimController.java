package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ClaimCustomerDetails;
import com.lti.dto.StatusClaim;
import com.lti.entity.Claim;
import com.lti.entity.NewPolicy;
import com.lti.entity.Policy;
import com.lti.exception.ClaimException;
import com.lti.service.ClaimService;
import com.lti.service.EmailServiceForChat;

@RestController
@CrossOrigin
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private EmailServiceForChat emailServiceForChat;

	@GetMapping("/claimpage")
	public List<ClaimCustomerDetails> displayOnClaimPage(@RequestParam("customerId") int id) {
		try {
		List<NewPolicy>listPolicy=claimService.displayOnClaimPage(id);
		List<ClaimCustomerDetails> listclaimCustDetails= new ArrayList<>();
		if(listPolicy.size()==0)
			throw new ClaimException("no policy present");
			for(int i=0;i<listPolicy.size();i++) {
			ClaimCustomerDetails claimCustDetails=new ClaimCustomerDetails();
			claimCustDetails.setStatus(true);
			claimCustDetails.setPolicyNo(listPolicy.get(i).getPolicyNo());
			claimCustDetails.setPolicyId(listPolicy.get(i).getPolicy().getPolicyId());
			claimCustDetails.setPolicyType(listPolicy.get(i).getPolicy().getPolicyType());
			claimCustDetails.setVehicleType(listPolicy.get(i).getVehicle().getVehicleType());
			claimCustDetails.setManufacturer(listPolicy.get(i).getVehicle().getManufacturer());
			claimCustDetails.setModel(listPolicy.get(i).getVehicle().getModel());
			claimCustDetails.setRegistrationNo(listPolicy.get(i).getVehicle().getRegistrationNo());
			listclaimCustDetails.add(claimCustDetails);
			}
		
		return listclaimCustDetails;
		}
		catch(ClaimException e) {
			List<ClaimCustomerDetails> listclaimCustDetails= new ArrayList<>();
			ClaimCustomerDetails claimCustDetails=new ClaimCustomerDetails();
			claimCustDetails.setStatus(false);
			listclaimCustDetails.add(claimCustDetails);
			return  listclaimCustDetails;
		}

	}

	@GetMapping("/claimstatus")
	public StatusClaim displayOnClaiming(@RequestParam("policyNo") int policyNo,
			@RequestParam("reason") String reason) {
		try {
			if(claimService.isClaimPending(policyNo)) 
				throw new ClaimException("user cannot claim multiple time before approval of claim");
			if(claimService.hasPolicyExpired(policyNo)) {
				throw new ClaimException("policy has expired you cannot claim");
			}
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
			statusClaim.setStatusMessage("policy doesnot exist wrong policy number or you have already claimed and awaiting approval");
			return statusClaim;

		}

	}
	
	
	@GetMapping("/claim-email")
	public String sendChatEmai(@RequestParam("chatMail") String chatMail, @RequestParam("policyNo") int policyNo) {
		try {
			emailServiceForChat.sendMailForChat(chatMail, policyNo);
			return "Email Sent";
			
		}
		catch(ClaimException e) {
			return "Try Again later";
			
		}
		
	}
	
}
