package com.henryschein.notificationService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.henryschein.userservice.constant.UserConstants;

@Entity
@Table(name= "henrynotification")

public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    @NotBlank(message = "EMAIL_REQUIRED_MSG")
    @Email(message = "EMAIL_REQUIRED_MSG")
    private String toEmail;
    
    @NotBlank(message = "Email Subject required")
    private String subject;
    
    @NotBlank(message= "Email body required")
    @Size(max = 500 , message = "Email body required")
    private String emailBody;
	
	private boolean isSent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public boolean isSent() {
		return isSent;
	}

	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}
	
	

}
