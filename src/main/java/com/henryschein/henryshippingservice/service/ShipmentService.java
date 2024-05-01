package com.henryschein.henryshippingservice.service;

import java.util.List;
import java.util.Optional;

import com.henryschein.henryshippingservice.model.Shipment;
import com.henryschein.orderservice.model.Order;


public interface ShipmentService {
	//Get Orders
	public List<Shipment> getAllShipments();
	public Optional<Shipment> getShipmentByShipmentId(long id);
	
	//Create Order
	public Shipment createShipment(Shipment shipment);
	public List<Shipment> createShipments(List<Shipment> shipments);
	
	//kafka
	public void publishToTopic(String jsonOrder);
	public void consumefromTopic(String message);
	
	//update Orders
	public Shipment updateOrder(Shipment shipment);
	public List<Shipment> updateOrders(List<Shipment> shipments);
	
	//Delete Order
	public String deleteShipment(long id);
	

}
