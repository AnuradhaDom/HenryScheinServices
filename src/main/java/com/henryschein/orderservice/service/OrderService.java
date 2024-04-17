package com.henryschein.orderservice.service;
import java.util.List;
import java.util.Optional;

import com.henryschein.orderservice.model.Order;

public interface OrderService {
	//Get Orders
	public List<Order> getAllOrders();
	public Optional<Order> getOrderById(long id);
	
	//Create Order
	public Order createOrder(Order order);
	public List<Order> createOrders(List<Order> orders);
	
	//update Orders
	public Order updateOrder(Order order);
	public List<Order> updateOrders(List<Order> orders);
	
	//Delete Order
	public String deleteOrder(long id);
	

}
