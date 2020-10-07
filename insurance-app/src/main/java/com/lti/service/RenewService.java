package com.lti.service;

import java.time.LocalDate;
import java.time.Period;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.dao.BuyPolicyDao;
import com.lti.dao.RenewDao;
import com.lti.dto.PaymentRenew;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Policy;
import com.lti.exception.RenewException;

@Component
@Transactional
public class RenewService {
	@Autowired
	private RenewDao renewDao;
	
	@Autowired
	private BuyPolicyDao buyPolicyDao;

	public boolean isPolicyExisting(int policyNo) {
		return renewDao.isPolicyExisting(policyNo);
	}

	public boolean hasPolicyExpired(int policyNo) {
		LocalDate dateOfPayment = renewDao.paymentDate(policyNo);
		int policyDuration = renewDao.fetchPolicyDurationByPolicyNo(policyNo);
		Period date = Period.between(dateOfPayment, LocalDate.now());
		if (date.getYears() > policyDuration) {
			return true;
		} else {
			return false;
		}

	}

	public NewPolicy renewPolicy(int policyNo, int newPolicyDuration) {
		try {
		NewPolicy oldPolicyDetails = renewDao.fetchById(NewPolicy.class, policyNo);
		Policy oldPolicy = oldPolicyDetails.getPolicy();
		String oldPolicyType = oldPolicy.getPolicyType();
		Policy newPolicy = renewDao.fetchPolicy(oldPolicyType, newPolicyDuration);
		oldPolicyDetails.setPolicy(newPolicy);
		return renewDao.save(oldPolicyDetails);
		}
		catch(RenewException e) {
			throw new RenewException("could not renew the policy");
		}
	}
	
	public Payment makePayment(PaymentRenew paymentRenew) {
		try {
		Payment payment= new Payment();
		payment.setAmount(paymentRenew.getAmount());
		payment.setNewPolicy(paymentRenew.getNewPolicy());
		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentId(paymentRenew.getPaymentId());
		payment.setPaymentMode(paymentRenew.getPaymentMode());
		return buyPolicyDao.save(payment);
		}
		catch(RenewException e) {
			throw new RenewException("payment not sucessful");
		}
	}
	public double premiumAmount(int policyNo,int newPolicyDuration) {
		int oldPolicyDuration = renewDao.fetchPolicyDurationByPolicyNo(policyNo);
		double amount= renewDao.fetchAmountPaid(policyNo);
		double newAmount= amount*newPolicyDuration/oldPolicyDuration;
		return newAmount;
		
	}

}
