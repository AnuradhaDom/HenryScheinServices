package com.henryschein.notificationService.notificationserviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henryschein.notificationService.notificationservice.MailService;

import org.springframework.mail.SimpleMailMessage;
import org.slf4j.LoggerFactory;

import org.springframework.mail.MailSender;


@Service

public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailSender mailSender;
	

	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	


	@Override
	public void sendMail(final String to, final String subject, final String text) {
		try {
			final SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(to);
			email.setSubject(subject);
			//email.setFrom("vinodthorat321@gmail.com");
			email.setText(text);
			mailSender.send(email);
		LOGGER.info("SENT EMAIL: TO={}|SUBJECT:{}|TEXT:{}", to, subject, text);
	} catch (final Exception e) {
		LOGGER.error("Error sending email", e);
		throw e;
	}
				
	}

}
