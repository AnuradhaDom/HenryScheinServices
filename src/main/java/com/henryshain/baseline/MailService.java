package com.henryshain.baseline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private MailSender mailSender;

	public static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

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
