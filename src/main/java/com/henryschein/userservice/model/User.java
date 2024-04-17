package com.henryschein.userservice.model;

import com.henryschein.userservice.constant.UserConstants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "henryuser")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = UserConstants.USERNAME_REQUIRED_MSG)
    @Size(min = 2, max = 100, message = UserConstants.USERNAME_LENGTH_MSG)
    private String userName;

    @Column(unique = true)
    @NotBlank(message = UserConstants.EMAIL_REQUIRED_MSG)
    @Email(message = UserConstants.EMAIL_INVALID_MSG)
    private String email;

   
	@NotBlank(message = UserConstants.PASSWORD_REQUIRED_MSG)
    @Size(min = 8, message = UserConstants.PASSWORD_LENGTH_MSG)
    private String password;

    private boolean active;

    @NotBlank(message = UserConstants.ROLES_REQUIRED_MSG)
    private String roles;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	


}