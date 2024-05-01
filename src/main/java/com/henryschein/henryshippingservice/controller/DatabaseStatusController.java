package com.henryschein.henryshippingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henryschein.henryshippingservice.model.DatabaseStatus;
import com.henryschein.henryshippingservice.service.DatabaseStatusService;

@RestController
@RequestMapping("v1/dbstatus")
public class DatabaseStatusController {
	@Autowired
	private DatabaseStatusService dbService;
	
	@GetMapping
	public List<DatabaseStatus> getStatus() {
		return dbService.getStatus();
	}
	
	@PostMapping("/addStatus/{isDbUp}")
	public DatabaseStatus addStatus(@PathVariable boolean isDbUp) {
		return dbService.addStatus(isDbUp);
	}
	
	@PutMapping("/updateStatus/{isDbUp}")
	public DatabaseStatus updateStatus(@PathVariable boolean isDbUp){
		return dbService.updateStatus(isDbUp);
	}

}
