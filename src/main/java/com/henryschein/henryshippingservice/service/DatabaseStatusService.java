package com.henryschein.henryshippingservice.service;

import java.util.List;

import com.henryschein.henryshippingservice.model.DatabaseStatus;

public interface DatabaseStatusService {
	//Get Status
	public List<DatabaseStatus> getStatus();
	
	//Post Status
	public DatabaseStatus addStatus(boolean isDbUp);
	
	//Update Status
	public DatabaseStatus updateStatus(boolean isDbUp);

}
