package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	public void Mailer(String email, String info) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("bigladproject@outlook.com");
		message.setTo(email);
		message.setSubject("Do-Not-Reply to this mail");
		message.setText("Greetings, " + info);
		mailSender.send(message);
		System.out.println("Mail sent");
	}
}
