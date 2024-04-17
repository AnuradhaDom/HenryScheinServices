package com.henryschein.notificationService.notificationservice;

public interface NotificationService {
	
	public void publishToTopic(String message);
	public void consumefromTopic(String message);
	

}
