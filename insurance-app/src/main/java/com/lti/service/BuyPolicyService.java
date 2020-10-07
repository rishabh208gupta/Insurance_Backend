package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.BuyPolicyDao;
import com.lti.dto.PremiumAnswer;
import com.lti.dto.PremiumStatus;
import com.lti.entity.Customer;
import com.lti.entity.NewPolicy;
import com.lti.entity.Payment;
import com.lti.entity.Policy;
import com.lti.entity.Vehicle;
import com.lti.exception.BuyPolicyException;

@Service
public class BuyPolicyService {

	@Autowired
	private BuyPolicyDao buyPolicyDao;
	
	@Autowired
	private CalculatePremiumService calculatePremiumService;
	
	@Transactional
	public Vehicle registerVehicle(Vehicle vehicle) {
		if(!buyPolicyDao.isVehiclePresent(vehicle.getChasisNo())) {
			Customer customer = buyPolicyDao.fetchById(Customer.class, vehicle.getCustomer().getCustomerId());
			System.out.println(vehicle.getCustomer().getCustomerId());
			vehicle.setCustomer(customer);
			return buyPolicyDao.save(vehicle);
		}
		else {
			throw new BuyPolicyException("vehicle already registered");
		}
	}
	
	@Transactional
	public NewPolicy registerPolicy(NewPolicy newPolicy) {
		
			Policy policy = buyPolicyDao.getPolicy(newPolicy);
			Vehicle vehicle = buyPolicyDao.fetchById(Vehicle.class, newPolicy.getVehicle().getVehicleId());
			newPolicy.setPolicy(policy);
			newPolicy.setVehicle(vehicle);
			return buyPolicyDao.save(newPolicy);
		
	}
	
	public NewPolicy getBillDetails(int policyNo) {
		return buyPolicyDao.fetchById(NewPolicy.class, policyNo);
	}
	
	@Transactional
	public Payment makePayment(Payment payment) {
		NewPolicy newPolicy = buyPolicyDao.fetchById(NewPolicy.class,payment.getNewPolicy().getPolicyNo());
		payment.setNewPolicy(newPolicy);
		return buyPolicyDao.save(payment);
	}
	
	public PremiumStatus calculatePremium(int vehicleId) {
		Vehicle vehicle = buyPolicyDao.fetchById(Vehicle.class, vehicleId);
		PremiumStatus premiumStatus = new PremiumStatus();
		
		if(vehicle.getVehicleType()=="2-wheeler") {
			int price = 50000;
			PremiumAnswer resultComp1 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 7, 1) ;
			premiumStatus.setIdvComp1(resultComp1.getIdv());
			premiumStatus.setAmountComp1(resultComp1.getEstimatedValue());
			
			PremiumAnswer resultComp2 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 7, 2) ;
			premiumStatus.setIdvComp2(resultComp2.getIdv());
			premiumStatus.setAmountComp2(resultComp2.getEstimatedValue());
			
			PremiumAnswer resultComp3 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 7, 3) ;
			premiumStatus.setIdvComp3(resultComp3.getIdv());
			premiumStatus.setAmountComp3(resultComp3.getEstimatedValue());
			
			PremiumAnswer resultParty1 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 6.5, 1) ;
			premiumStatus.setIdvParty1(resultParty1.getIdv());
			premiumStatus.setAmountParty1(resultParty1.getEstimatedValue());
			
			PremiumAnswer resultParty2 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 6.5, 2) ;
			premiumStatus.setIdvParty2(resultParty2.getIdv());
			premiumStatus.setAmountParty2(resultParty2.getEstimatedValue());
			
			PremiumAnswer resultParty3 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 6.5, 3) ;
			premiumStatus.setIdvParty3(resultParty3.getIdv());
			premiumStatus.setAmountParty3(resultParty3.getEstimatedValue());
			
			return premiumStatus;
			
		}
		else if(vehicle.getVehicleType()=="4-wheeler") {
			int price = 400000;
			
			PremiumAnswer resultComp1 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 7, 1) ;
			premiumStatus.setIdvComp1(resultComp1.getIdv());
			premiumStatus.setAmountComp1(resultComp1.getEstimatedValue());
			
			PremiumAnswer resultComp2 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 7, 2) ;
			premiumStatus.setIdvComp2(resultComp2.getIdv());
			premiumStatus.setAmountComp2(resultComp2.getEstimatedValue());
			
			PremiumAnswer resultComp3 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 7, 3) ;
			premiumStatus.setIdvComp3(resultComp3.getIdv());
			premiumStatus.setAmountComp3(resultComp3.getEstimatedValue());
			
			PremiumAnswer resultParty1 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 6.5, 1) ;
			premiumStatus.setIdvParty1(resultParty1.getIdv());
			premiumStatus.setAmountParty1(resultParty1.getEstimatedValue());
			
			PremiumAnswer resultParty2 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 6.5, 2) ;
			premiumStatus.setIdvParty2(resultParty2.getIdv());
			premiumStatus.setAmountParty2(resultParty2.getEstimatedValue());
			
			PremiumAnswer resultParty3 =calculatePremiumService.calculate(price, vehicle.getPurchaseDate(), 6.5, 3) ;
			premiumStatus.setIdvParty3(resultParty3.getIdv());
			premiumStatus.setAmountParty3(resultParty3.getEstimatedValue());
			
			return premiumStatus;
		}
		
		else {
			throw new BuyPolicyException("could not calculate premium");
		}
	}
}
