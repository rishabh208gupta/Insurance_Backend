package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	public void sendMailForNewRegistration(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("do-not-reply@myproject.com");
		message.setTo(customer.getEmail());
		message.setSubject("Thank u for registering with us..");
		message.setText("Greetings" + customer.getName());
		mailSender.send(message);
	}
}
