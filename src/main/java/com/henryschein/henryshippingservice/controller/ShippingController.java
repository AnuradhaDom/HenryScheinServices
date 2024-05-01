package com.henryschein.henryshippingservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henryschein.henryshippingservice.model.Shipment;
import com.henryschein.henryshippingservice.service.ShipmentService;
import com.henryschein.userservice.controller.UserController;
import com.henryschein.userservice.model.User;

@RestController
@RequestMapping("v1/shipment")

public class ShippingController {
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
    ShipmentService shipmentService;
	
	@GetMapping
	public List<Shipment> getShipments(){
		return shipmentService.getAllShipments();
	}
	
	@GetMapping("/getshipmentbyid/{id}")
	public Optional<Shipment> getShipmentById(@PathVariable long id) {
		return shipmentService.getShipmentByShipmentId(id);
	}
	
	@PostMapping("/createshipment")
	public Shipment createShipment(@RequestBody Shipment shipment) {
		// Capture input time
    	long inputTime = System.currentTimeMillis();
    	
    	Shipment createdShipment = shipmentService.createShipment(shipment);
    	
    	// Capture output time
        long outputTime = System.currentTimeMillis();

        // Calculate processing time
        long processingTime = outputTime - inputTime;

        // Log input and output time
        logger.info("createShipment Input time: {}", inputTime);
        logger.info("createShipment Output time: {}", outputTime);
        logger.info("createShipment Processing time: {} milliseconds", processingTime);

		return createdShipment;
	}
	
	
	@PostMapping("/publishtoTopic")
	public String createShipment(@RequestBody String shipment) {
		// Capture input time
    	long inputTime = System.currentTimeMillis();
    	
		shipmentService.publishToTopic(shipment);
		
		// Capture output time
        long outputTime = System.currentTimeMillis();

        // Calculate processing time
        long processingTime = outputTime - inputTime;

        // Log input and output time
        logger.info("Shipment sending to publishtoTopic of shipment Input time: {}", inputTime);
        logger.info("publishtoTopic of shipment Output time: {}", outputTime);
        logger.info("publishtoTopic of shipment Processing time: {} milliseconds", processingTime);

		
		return "SUCCESSFULLY POSTED TO QUEUE OF SHIPMENT";
	}
	
	
	

}
