package com.henryschein.notificationService.controller;

import java.util.List;

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

@RestController
@RequestMapping("v1")
public class NotificationController {
	
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
		notificationService.publishToTopic(message);
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
