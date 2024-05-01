package com.henryschein.notificationService.notificationserviceImpl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.henryschein.notificationService.entity.Notification;
import com.henryschein.notificationService.notificationservice.MailService;
import com.henryschein.notificationService.notificationservice.NotificationService;
import com.henryschein.notificationService.repository.NotificationRepository;

import org.json.JSONException;
import org.json.JSONObject;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	
	
public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
    
	@Autowired
	MailService mailService;
	
	public static final String topic = "mytopic";
	
	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public void publishToTopic(String message) { //to:email, subject:your order, body:order details
		System.out.println("Publishing to the topic : " + topic);
		this.kafkaTemplate.send(topic, message);
	}
   
	
	@Override
	@KafkaListener(topics = "mytopic", groupId = "mygroup")
	public void consumefromTopic(String message) {
		
		try {
			
		
		
		/*try
		 * 
		 * 
		 * 
		 * 
		 *try{
		 *  save 
		 *  } catch (){
		 *  	file storage system - store , // message // support email 
		 *   local like logs file each message will have separate file
		 *   write schedular for this file to process - call publishToTopic
		 *  }
		 * 
		 * 
		 * 
		 * catch{
		 * 	logger.error(message)
		 * }
		 */
		//save - error
		//catch - print message
		
		///not there message -> file system
		
		
		System.out.println("we got the message and consumed this message : " + message);
		
		JSONObject jsonObject = new JSONObject(message);
		
		
		
		// Check if the necessary keys exist in the JSON object
        if (jsonObject.has("toEmail") && jsonObject.has("subject") && jsonObject.has("emailBody")) {
            // Extract values from the JSON object
        
		
		
        
		String toEmail = jsonObject.getString("toEmail");
        String subject = jsonObject.getString("subject");
        String emailBody = jsonObject.getString("emailBody");

        System.out.println("INSIDE consumefromTopic method : " + message);

		
		//CONVERT JSONSTRING TO GET VALUES FROM JSONSTRING or MAP TO NOTIFICATION OBJECT
		
		//postman (//to:email, subject:your order, body:order details)  -> write controller ->  publishToTopic
		
		//to:email    subject:your order    body:order     split by ,
		
		//split by :
		// to this email 
		// for loop and if condtion to check to or body or subject
		
		//String email, subject, body = null;
		
		mailService.sendMail(toEmail, subject, emailBody);
		
		
        System.out.println("EMAIL SENT FROM CONSUMER METHOD");

		Notification notification = new Notification();
		
		notification.setSubject(subject);
		notification.setToEmail(toEmail);
		notification.setEmailBody(emailBody);
		notification.setSent(true);
		
		notificationRepository.save(notification);
		// set false 
		//mailService.sendMail(to, subject, body);
		
		//set isSent = true
		//to, subject, body, sent (true/false)
		//notification.save() table 
		
		System.out.println("Email sent and notification saved successfully.");
        }else {
            // Handle missing keys in the JSON object
            System.out.println("Required keys not found in the JSON message.");
        }
	
	}catch (JSONException e) {
        // Handle JSON parsing errors
        System.out.println("Error parsing JSON message: " + e.getMessage());
    }
	
	
	
	}


}
