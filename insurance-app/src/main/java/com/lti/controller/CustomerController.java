package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.dto.CheckOtp;
import com.lti.dto.StatusDto;
import com.lti.dto.StatusDto.StatusType;
import com.lti.service.CustomerService;

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/checkotp")
    public StatusDto validateOtp(@RequestBody CheckOtp checkOtp) {
        if (customerService.validateOtp(checkOtp.getOtp())) {
            System.out.println("back from service to controller");
            StatusDto status = new StatusDto();
            status.setStatus(StatusType.Success);
            return status;
        }
        StatusDto status = new StatusDto();
        status.setStatus(StatusType.Failure);
        return status;
    }

}
