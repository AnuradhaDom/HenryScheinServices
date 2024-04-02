package com.henryshain.baseline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class JavaController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(JavaController.class);

	
	public static final String topic = "mytopic";
	
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	UserBaseRepository userRepository;

	@Autowired
	MailService mailService;
	
	
	@PostMapping("/send/{msg}")
	public void sendtoKafkaTopic(@PathVariable String msg) {
		publishToTopic(msg);
	}
	
	public void publishToTopic(String message) {
		System.out.println("Publishing to the topic : " + topic);
		this.kafkaTemplate.send(topic, message);
	}
	
	@GetMapping("/getUserDetails/{userName}")
	public UserBase getName(@PathVariable String userName) throws Exception {
		
		UserBase userBase = userRepository.findByName(userName);
		Iterable<UserBase> userList = userRepository.findAll();
		if(userBase != null) {
			return userBase;
		}else {
			throw new Exception("user not found");
		}
		
	}
	
	
	@PostMapping("/sendNotification")
	public String notificationService() {
		
		mailService.sendMail("anuradhathorat15@gmail.com", "hello how are you", "Hello Anu, hope you are doing well!");
		return "sent successfully";
	}
	
	
	
	@PostMapping("/users")
    public String createUser(@RequestBody UserBase userBase) {
        // Process the user object received in the request body
		userRepository.save(userBase);
		
        return "User created: " + userBase.getName();
    }
	
	@GetMapping("/getAllusers")
	public Iterable<UserBase> getAllusers() throws Exception {
		
		Iterable<UserBase> userList = userRepository.findAll();
		
		return userList;
		
	}
	
	@PostMapping("/addProduct")
	public String addProduct() throws Exception {
		
		LOGGER.info("please add this product in the db");
		try {
			
			UserBase userBase = new UserBase();
			userBase.setId(1);
			userBase.setName("vinod");
			userBase.setEmail("vinodthoar@gmail.com");
			
			userRepository.save(userBase);
			
			extracted();
			
			return "product added successfully";
			
		}catch (Exception e) {
			//System.out.println("error while adding product");
			LOGGER.error("this is the problem : ", e);
			throw new Exception("this is the problem");
			//Exception table me entry
		}
		
	}

	@PostMapping("/addUser")
	public String addUser() throws Exception {

		LOGGER.info("please add this product in the db");
		try {

			UserBase user = new UserBase();
			user.setId(1);
			user.setName("vinod");
			user.setEmail("vinodthoar@gmail.com");

			userRepository.save(user);

			//extracted();

			return "product added successfully";

		}catch (Exception e) {
			//System.out.println("error while adding product");
			LOGGER.error("this is the problem : ", e);
			throw new Exception("this is the problem");
			//Exception table me entry
		}

	}

	private void extracted() throws Exception {
		throw new Exception();
	}
	
	

}
