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
    
    

	public User(int id,
			@NotBlank(message = "Username is required") @Size(min = 2, max = 100, message = "Username must be between 2 to 50 characters") String userName,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password,
			boolean active, @NotBlank(message = "Roles are required") String roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public User(int i, String userName, String password, String email) {
		// TODO Auto-generated constructor stub
		
		super();
		this.id = i;
		this.userName = userName;
		this.email = email;
		this.password = password;

	}
	
	

	public User() {
		super();
	}

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