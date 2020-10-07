package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.NewPolicy;
import com.lti.entity.Vehicle;




@Component
public class DashboardDao extends GenericDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Transactional
	public int save(Admin admin){
			 Admin updatedAdmin =(Admin) entityManager.merge(admin);
			 return updatedAdmin.getAdminId();
		}
	
	
	public Admin findById(int adminId) {
		return entityManager.find(Admin.class, adminId);
	}
	
	
	public int findByUserNameAndPassword(String username,String password) {
		return (Integer)entityManager.createQuery("select a.adminId from Admin a where a.username=:un and a.password=:pw").setParameter("un", username).setParameter("pw", password).getSingleResult();
	}
	
	
	
	public boolean isAdminPresent(String username) {
		return (Long)entityManager.createQuery("select count(a.adminId) from Admin a where a.username=:un ").setParameter("un", username).getSingleResult()==1? true:false;
	}
	
	public List<Claim> fetchAllClaimDetails() {
		return entityManager
				.createQuery("select c from Claim c ").getResultList();
	}
	
	public Customer fetchCustomerDetailsfromClaimId(int claimId) {
		return (Customer) entityManager
				.createQuery("select c from Claim cl join cl.newPolicy "
						+ "n join n.vehicle v join v.customer c where cl.claimId=:claimId ")
				.setParameter("claimId", claimId)
				.getSingleResult();
		
	}
	
	public Vehicle fetchVehicleDetailsfromClaimId(int claimId) {
		return (Vehicle) entityManager
				.createQuery("select v from Claim cl join cl.newPolicy "
						+ "n join n.vehicle v where cl.claimId=:claimId ")
				.setParameter("claimId", claimId)
				.getSingleResult();
		
	}
	

}
