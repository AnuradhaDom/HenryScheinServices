package com.henryschein.orderservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henryschein.orderservice.model.Order;
import com.henryschein.orderservice.service.OrderService;

@RestController
@RequestMapping("v1/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping
	public List<Order> getOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/getOrderById/{id}")
	public Optional<Order> getOrderById(@PathVariable long id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping("/createOrder")
	public Order createOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}
	
	@PostMapping("/createOrders")
	public List<Order> createOrders(@RequestBody List<Order> orders){
		return orderService.createOrders(orders);
	}
	
	@PutMapping("/updateOrder")
	public Order updateOrder(@RequestBody Order order) {
		return orderService.updateOrder(order);
	}
	
	@PutMapping("/updateOrders")
	public List<Order> updateOrders(@RequestBody List<Order> orders){
		return orderService.updateOrders(orders);
	}
	
	@DeleteMapping("/deleteOrder")
	public String deleteOrder(@PathVariable long id) {
		return orderService.deleteOrder(id);
	}


}
