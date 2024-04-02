package com.henryshain.baseline;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	
	
	@KafkaListener(topics = "mytopic", groupId = "mygroup")
	public void consumefromTopic(String message) {
		System.out.println("we got the message and consumed this message : " + message);
	}

}
