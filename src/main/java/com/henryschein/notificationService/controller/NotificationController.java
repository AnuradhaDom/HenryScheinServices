package com.henryschein.notificationService.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.henryschein.notificationService.entity.Notification;
import com.henryschein.notificationService.notificationservice.MailService;
import com.henryschein.notificationService.notificationservice.NotificationService;
import com.henryschein.notificationService.repository.NotificationRepository;
import com.henryschein.userservice.controller.UserController;

@RestController
@RequestMapping("v1")
public class NotificationController {
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@GetMapping("/getnotification")
	public List<Notification> getNotification() {
		return notificationRepository.findAll();
		
	}
	
	@PostMapping("/publishtotopic")
	public String publishToTopic(@RequestBody String message) {
		
		// Capture input time
    	long inputTime = System.currentTimeMillis();
    	
    	notificationService.publishToTopic(message);
    	
    	// Capture output time
        long outputTime = System.currentTimeMillis();

        // Calculate processing time
        long processingTime = outputTime - inputTime;

        // Log input and output time
        logger.info("Notificationservice's publishToTopic Input time: {}", inputTime);
        logger.info("Notificationservice's publishToTopic Output time: {}", outputTime);
        logger.info("Notificationservice's publishToTopic Processing time: {} milliseconds", processingTime);

		
		return "successfully sent a message to a topic" ;
	}
	
	@PostMapping("/consumefromtopic")
	public String consumeFromTopic(@RequestBody String message) {
		notificationService.consumefromTopic(message);
		return "successfully message consumed from a topic";
	}
	
	@PostMapping("/sendemail")
	public String sendEmail(@RequestParam final String to, @RequestParam final String subject, @RequestParam final String text) {
		mailService.sendMail(to , subject , text);
		return "successfully email sent!";

		
	}

}
