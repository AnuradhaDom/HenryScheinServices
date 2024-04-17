package com.henryschein.orderservice.serviceImpl;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.apache.catalina.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.henryschein.notificationService.notificationservice.NotificationService;
import com.henryschein.orderservice.constants.Constants;
import com.henryschein.orderservice.customeexception.CustomeException;
import com.henryschein.orderservice.model.Order;
import com.henryschein.orderservice.repository.OrderRepository;
import com.henryschein.orderservice.service.OrderService;
import com.henryschein.productservice.constant.ProductConstants;
import com.henryschein.productservice.customeexception.CustomException;
import com.henryschein.productservice.model.Product;
import com.henryschein.orderservice.errorcodes.ErrorCodes;
import com.henryschein.productservice.service.ProductService;
import com.henryschein.userservice.service.UserService;
import com.henryschein.userservice.model.User;

import ch.qos.logback.classic.Logger;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	

	@Override
	public List<Order> getAllOrders() {
		try{
			List<Order> orders = orderRepository.findAll();
			LOGGER.info("successfully getAllOrders method executed {}", orders);
			return orders;
		}
		catch(Exception e) {
			LOGGER.error("Error occurred while fetching Orders: {}" ,e);
	        throw new CustomException( "Order not found : " + e );
		}
		
	}

	@Override
	public Optional<Order> getOrderById(long id) {
		try {
			Optional<Order> order = orderRepository.findById(id);
            LOGGER.info("successfully getOrderById method executed {}", order);
            return order;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching order by Id: {}" ,e);
            throw new CustomException("order not found with id: " + e);
        }
   
	}

	@Override
	public Order createOrder(Order order) {
		try { 
			
			
			
			String username = order.getCustomerName();
			Optional<User> userData  = userService.getUser(username);
		      
		   
			
			// Get the current authentication object from SecurityContextHolder
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	        // Check if the authentication object is not null and is an instance of UserDetails
//	        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//	            // Cast the principal to UserDetails
//	            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//	        
//	            // Get the username from UserDetails
//	            username = userDetails.getUsername();
//	            
//	            LOGGER.info("userDetails  executed {}", username);
//	            Optional<User> userData  = userService.getUser(username);
//	      
//	            if (userData.isPresent()) { // Check if the Optional contains a value
//	                User user = userData.get(); // Retrieve the User object from the Optional
//	                 // Access the email from the User object
//	                 System.out.println("Customer Email Id " + user.getEmail());
//	                 
//	                 
//	                 Order userInfo = null;
//	                 userInfo.setEmailId(user.getEmail());
//	                 userInfo.setCustomerName(user.getUserName());
//	                 orderRepository.save(userInfo);
//	                 
//	                } else {
//	                LOGGER.error("User Not Found!");// Handle the case where no user is found for the given username
//	               //return "User not found";
//	                   }
//	            
//	        }
	        
	        //product1:10, product2:20
	        
	        //product1:10  -> p1, 10
	        //product2:20
	        LOGGER.info("Product information ");
	        String orderProducts = order.getProducts();
	        for(String productsDetails : orderProducts.split(",")) {
	        	String[] product =  productsDetails.split(":");
	        	String productName = product[0];
	        	int productQua = Integer.parseInt(product[1]);
	        	
	        	Product productDetail = productService.getProductsByname(productName);
	        	
	        	if(productDetail.getQuantity() != 0 && productDetail.getQuantity() >= productQua ) {
	        		int quantity = productDetail.getQuantity();
	        		int updtedQua = quantity - productQua ;
	        		productDetail.setQuantity(updtedQua);
	        		
	        		productService.updateProduct(productDetail);
	        		
	        		 if (userData.isPresent()) { // Check if the Optional contains a value
	      		       User user = userData.get(); // Retrieve the User object from the Optional
	      		                 // Access the email from the User object
	      		       System.out.println("Customer Email Id " + user.getEmail());
	      		      
	      			//Order userInfo = new Order();
	      			order.setEmailId(user.getEmail());
	      			order.setCustomerName(user.getUserName());
	                  LOGGER.info("Befor User Save");
	                  orderRepository.save(order);
	                  LOGGER.info("User Saved succesfully");
	                  
	                  
	                  String productdetails = order.getProducts();
	                  String orderId = String.valueOf(order.getOrderId());
	                //1. create jsonstring, SEND JSON STRING and convert to object in notifcation service
	                  
	                  //2. CALL REST API 
	                  String toEmail = user.getEmail();
	                  String subject = "HenrySchein products order details";
	                  String emailBody = "Thank you for your order! Here are the details" + productdetails + "\n Order Id: " + orderId;
	                  
	               // Create a JSONObject and add fields
	                  JSONObject jsonObject = new JSONObject();
	                  jsonObject.put("toEmail", toEmail);
	                  jsonObject.put("subject", subject);
	                  jsonObject.put("emailBody", emailBody);
  
	               // Convert JSONObject to JSON string
	                  String jsonString = jsonObject.toString();

	                  System.out.println(jsonString);

	                  
	                  notificationService.publishToTopic(jsonString);
	                  
	      		    }else {
	                     LOGGER.error("User Not Found!");// Handle the case where no user is found for the given username
	                    //return "User not found";
	                         }
	                  
	        	}else {
	        		
	        		LOGGER.error("Order not created");
	        		throw new CustomException("order not submitted: ");
	        		   
	        		
	        	}
	        }
	        return order;
	        
		
//	        
//	        String orderProducts = order.getProducts();
//	     
//	        for(String productName : orderProducts.split(",") ) {
//	        	
//	        	String[] p = productName.split(":");
//	        	String p1 = p[0];
//	        	int qua = Integer.parseInt(p[1]);
//	        	
//	        	Product product = productService.getProductsByname(productName);
//	        	if(product.getQuantity() != 0 ) {
//	        	int quantity = product.getQuantity();
//	        	//quantity++;
//	        	int newQ = quantity - qua;
//	        	product.setQuantity(newQ);
//	    
//	        	productService.updateProduct(product);
//	        	}
//	        	
//	        	
//	        }
	        
	        
	        
	        
	        
	        //return "Success " + order.getOrderId();
	        
	        
		}
	       
    catch(Exception e){
        LOGGER.error("Error occurred while creating order: {}", order , e );
        throw new CustomException("order not submitted: " + e);
    }
		
	}

	@Override
	public List<Order> createOrders(List<Order> orders) {
        try { 
        	List<Order> updatedOrders = new ArrayList<Order>();

            
        	for (Order order : orders) {
        		
        		String username = order.getCustomerName();
    			Optional<User> userData  = userService.getUser(username);
       			LOGGER.info("Product information ");
    	        String orderProducts = order.getProducts();
    	        for(String productsDetails : orderProducts.split(",")) {
    	        	String[] product =  productsDetails.split(":");
    	        	String productName = product[0];
    	        	int productQua = Integer.parseInt(product[1]);
    	        	
    	        	Product productDetail = productService.getProductsByname(productName);
    	        	
    	        	if(productDetail.getQuantity() != 0 && productDetail.getQuantity() >= productQua ) {
    	        		int quantity = productDetail.getQuantity();
    	        		int updtedQua = quantity - productQua ;
    	        		productDetail.setQuantity(updtedQua);
    	        		
    	        		productService.updateProduct(productDetail);
    	        		
    	        		 if (userData.isPresent()) { // Check if the Optional contains a value
    	      		       User user = userData.get(); // Retrieve the User object from the Optional
    	      		                 // Access the email from the User object
    	      		       System.out.println("Customer Email Id " + user.getEmail());
    	      		      
    	      			//Order userInfo = new Order();
    	      			order.setEmailId(user.getEmail());
    	      			order.setCustomerName(user.getUserName());
    	                  LOGGER.info("Befor User Save");
    	                  orderRepository.save(order);
    	                  LOGGER.info("User Saved succesfully");
    	                  


    	                  
    	                  
    	                  
    	                  String productdetails = order.getProducts();
    	                  String orderId = String.valueOf(order.getOrderId());
    	                //1. create jsonstring, SEND JSON STRING and convert to object in notifcation service
    	                //2. CALL REST API 
    	                  String toEmail = user.getEmail();
    	                  String subject = "HenrySchein products order details";
    	                  String emailBody = "Thank you for your order! Here are the details: " + productdetails + "\n Order Id: " + orderId;
    	                  
    	               // Create a JSONObject and add fields
    	                  JSONObject jsonObject = new JSONObject();
    	                  jsonObject.put("toEmail", toEmail);
    	                  jsonObject.put("subject", subject);
    	                  jsonObject.put("emailBody", emailBody);
      
    	               // Convert JSONObject to JSON string
    	                  String jsonString = jsonObject.toString();

    	                  System.out.println(jsonString);

    	                  
    	                  notificationService.publishToTopic(jsonString);

    	                  
    	                  updatedOrders.add(order);
    	                  
    	      		    }else {
    	                     LOGGER.error("User Not Found!");// Handle the case where no user is found for the given username
    	                    //return "User not found";
    	                         }
    	                  
    	        	}else {
    	        		
    	        		LOGGER.error("Order not created");
    	        		throw new CustomException("order not submitted: ");
    	        		   
    	        		
    	        	}
    	        }
    	       
    		
    		      
        		
        		
        		
        		
        
        	
        	// Get the current authentication object from SecurityContextHolder
        	//String username = null;
			// Get the current authentication object from SecurityContextHolder
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	        // Check if the authentication object is not null and is an instance of UserDetails
//	        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//	            // Cast the principal to UserDetails
//	            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//	        
//	            // Get the username from UserDetails
//	            username = userDetails.getUsername();
//	            
//	            LOGGER.info("userDetails  executed {}", username);
//	            Optional<User> userData  = userService.getUser(username);
//	      
//	            if (userData.isPresent()) { // Check if the Optional contains a value
//	                User user = userData.get(); // Retrieve the User object from the Optional
//	                 // Access the email from the User object
//	                 System.out.println("Customer Email Id " + user.getEmail());
//	                 
//	                 
//	                 Order userInfo = null;
//	                 userInfo.setEmailId(user.getEmail());
//	                 userInfo.setCustomerName(user.getUserName());
//	                 
//	                 orderRepository.save(userInfo);
//	                 
//	                } else {
//	                LOGGER.error("User Not Found!");// Handle the case where no user is found for the given username
//	               //return "User not found";
//	                   }
//	            
//	        }
//	        
	        //product1:10, product2:20
	        
	        //product1:10  -> p1, 10
	        //product2:20
	        
//	        String orderProducts = order.getProducts();
//	        for(String productsDetails : orderProducts.split(",")) {
//	        	String[] product =  productsDetails.split(":");
//	        	String productName = product[0];
//	        	int productQua = Integer.parseInt(product[1]);
//	        	
//	        	Product productDetail = productService.getProductsByname(productName);
//	        	
//	        	if(productDetail.getQuantity() != 0 && productDetail.getQuantity() >= productQua ) {
//	        		int quantity = productDetail.getQuantity();
//	        		int updtedQua = quantity - productQua ;
//	        		productDetail.setQuantity(updtedQua);
//	        		
//	        		productService.updateProduct(productDetail);
//	        		
//	        	}
//	        }
        }
	        return updatedOrders;
        }
	        
		
    catch(Exception e){
        LOGGER.error("Error occurred while creating orders: {}", orders , e );
        throw new CustomException("orders not submitted: " + e);
    }
}

	@Override
	public Order updateOrder(Order order) {
		try{
			List<Order> existingOrders = orderRepository.findAll();
			for(Order existingOrder : existingOrders) {
				if(order.getOrderId() == existingOrder.getOrderId()) {
					existingOrder.setOrderDate(order.getOrderDate());
					existingOrder.setProducts(order.getProducts());
					Order updateOrder = orderRepository.save(existingOrder);
					LOGGER.info("order Updated {}", updateOrder);
		            return updateOrder;
				}
				
			}
		        } catch (Exception e) {
		            LOGGER.error("Error occurred while Updating order: {}" ,e);
		            throw new RuntimeException("Failed to Update Order");
		        }
		return null;
			
		}

	@Override
	public List<Order> updateOrders(List<Order> orders) {
		try {
			List<Order> updatedOrders = new ArrayList<>();
			for (Order order: orders) {
			Order existingOrder = orderRepository.findById(order.getOrderId()).orElse(null);
			existingOrder.setOrderDate(order.getOrderDate());
			existingOrder.setProducts(order.getProducts());
			Order updateOrder = orderRepository.save(existingOrder);
			LOGGER.info("order Updated {}", updateOrder);
			updatedOrders.add(updateOrder);
            
			}
			return updatedOrders;
			
		}catch (Exception e) {
            LOGGER.error("Error occurred while Updating orders: {}" ,e);
            throw new RuntimeException("Failed to Update Orders");
        }
	
			
	}

	@Override
	public String deleteOrder(long id) {
		try {
            Order order = orderRepository.findById(id).orElse(null);
            orderRepository.deleteById(id);
            LOGGER.info("Order Deleted {}", order);
            return "Order removed successfully!" + id;
        } catch (Exception e) {
            LOGGER.error("Error occurred while Deleting order by Id: {}" ,e);
            throw new CustomException("order not deleted: " + e);
        }	}

}
