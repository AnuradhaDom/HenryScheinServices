package com.henryschein.orderservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
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
import com.henryschein.userservice.controller.UserController;

@RestController
@RequestMapping("v1/order")
public class OrderController {
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

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
		
		// Capture input time
    	long inputTime = System.currentTimeMillis();
    	
    	Order createdOrder = orderService.createOrder(order);
    	
    	// Capture output time
        long outputTime = System.currentTimeMillis();

        // Calculate processing time
        long processingTime = outputTime - inputTime;

        // Log input and output time
        logger.info("createOrder Input time: {}", inputTime);
        logger.info("createOrder Output time: {}", outputTime);
        logger.info("createOrder Processing time: {} milliseconds", processingTime);

		return createdOrder;
	}
	
	@PostMapping("/createOrders")
	public List<Order> createOrders(@RequestBody List<Order> orders){
		
		// Capture input time
    	long inputTime = System.currentTimeMillis();
    	
    	List<Order> createdOrders = orderService.createOrders(orders);
    	
    	// Capture output time
        long outputTime = System.currentTimeMillis();

        // Calculate processing time
        long processingTime = outputTime - inputTime;

        // Log input and output time
        logger.info("createOrders Input time: {}", inputTime);
        logger.info("createOrders Output time: {}", outputTime);
        logger.info("createOrders Processing time: {} milliseconds", processingTime);

		return createdOrders;
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
