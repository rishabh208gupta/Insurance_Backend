package com.lti;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.lti.dao.ClaimDao;
import com.lti.entity.Customer;



public class ClaimTest {

	@Test
	public void claimButtonReturnTest() {
		ClaimDao claimDao = new ClaimDao();
		List<Object[]> claimReturn = claimDao.fetchClaimDetails(1);
		for (Object[] x : claimReturn) {
			System.out.println(x);
		}

	}

	@Test
	public void fetchCustomer() {
		
		ClaimDao claimDao=new ClaimDao();
		List<Customer> customer = claimDao.fetchCustomer();
		for (Customer c : customer) {
			System.out.println(c.getName());
		}

	}

}
