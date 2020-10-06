package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceForChat {
	@Autowired
	private MailSender mailSender;
	
	public void sendMailForChat(String chatBox,int customerId){
	
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("abc_12341983@outlook.com");
		message.setTo("ghatakshubhojit1@gmail.com");
		message.setSubject("Query from "+customerId);
		message.setText(chatBox);
		mailSender.send(message);
			
	}
}
