package com.henryschein.henryshippingservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dbstatus")
public class DatabaseStatus {
	@Id
	private long id;
	private boolean isDatabaseUp ;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isDatabaseUp() {
		return isDatabaseUp;
	}
	public void setDatabaseUp(boolean isDatabaseUp) {
		this.isDatabaseUp = isDatabaseUp;
	}
	
	

}
