package com.henryschein.henryshippingservice.serviceimpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henryschein.henryshippingservice.constants.Constants;
import com.henryschein.henryshippingservice.customeexception.CustomeException;
import com.henryschein.henryshippingservice.model.DatabaseStatus;
import com.henryschein.henryshippingservice.model.Shipment;
import com.henryschein.henryshippingservice.repository.ShipmentRepository;
import com.henryschein.henryshippingservice.service.DatabaseStatusService;
import com.henryschein.henryshippingservice.service.ShipmentService;


@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	@Autowired
	private DatabaseStatusService dbService;
	
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
		
	
	
	
	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ShipmentServiceImpl.class);

	@Override
	public List<Shipment> getAllShipments() {
		try{
			List<Shipment> shipments = shipmentRepository.findAll();
			LOGGER.info("successfully getAllShipments method executed {}", shipments);
			return shipments;
		}
		catch(Exception e) {
			String errorMessage = Constants.ORDER_NOT_FOUND;
			LOGGER.error("Error occurred while fetching shipments: {}" ,e);
            throw new CustomeException(errorMessage);

			
	     	}

	}

	@Override
	public Optional<Shipment> getShipmentByShipmentId(long id) {
		try{
			Optional<Shipment> shipment = shipmentRepository.findById(id);
			LOGGER.info("successfully getShipmentByShipmentId method executed {}", shipment);
			return shipment;
		}
		catch(Exception e) {
			String errorMessage = Constants.ORDER_NOT_FOUND;
			LOGGER.error("Error occurred while fetching shipment: {}" ,e);
            throw new CustomeException(errorMessage);

			}

	}

	@Override
	public Shipment createShipment(Shipment shipment) {
	    try {
	        // Get the current database status from the service or directly from the database
	    	 
	        List<DatabaseStatus> status = dbService.getStatus(); // Assuming you have a DatabaseStatusService
	        for(DatabaseStatus st : status) {
	        boolean dbStatus = st != null && st.isDatabaseUp();
	        LOGGER.info("DB status is: {}", dbStatus);
	        
	        if (dbStatus) {
	            shipmentRepository.save(shipment);
	            LOGGER.info("Shipment saved successfully");
	        } else {
	            LOGGER.error("Shipment is not saved because the database is down", shipment);
	            LOGGER.info("Shipment is not saved because the database is down",shipment);
	            System.out.println("This Shipment is not saved" + shipment );
	            
	            
	            
	            //Save Not Processed Shipment to File
	            saveShipmentToFile(shipment);
	        }
	        
	        }
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while creating shipment: {}", e);
	    }
	    return shipment;
	}
	
	
	//Save Shipment to File Method
	private void saveShipmentToFile(Shipment shipment) {
		ObjectMapper objectMapper = new ObjectMapper();
	    String folderPath = "ShipmentDataFailedToUpload"; // Folder name
	    String fileName = folderPath + "/shipment_OrderId_" + shipment.getOrderId() + ".json"; // File path within the folder
	    try {
	        File folder = new File(folderPath);
	        if (!folder.exists()) {
	            folder.mkdirs(); // Create the folder if it doesn't exist
	        }
	        FileWriter fileWriter = new FileWriter(fileName);
	        objectMapper.writeValue(fileWriter, shipment);
	        fileWriter.close();
	        String absoluteFolderPath = folder.getAbsolutePath(); // Get the absolute path of the folder
	        LOGGER.info("Shipment data saved to file: {}", fileName);
	        LOGGER.info("Folder path: {}", absoluteFolderPath); // Log the absolute path of the folder
	   
	    } catch (IOException e) {
	        LOGGER.error("Error occurred while saving shipment data to file: {}", e);
	    }
	}
	
	
	
	
	
	
	
	

	@Override
	public List<Shipment> createShipments(List<Shipment> shipments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shipment updateOrder(Shipment shipment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shipment> updateOrders(List<Shipment> shipments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteShipment(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static final String topic = "henryshipping";

	@Override
	public void publishToTopic(String message) {
		System.out.println("Publishing to the topic : " + topic);
		this.kafkaTemplate.send(topic, message);
		
	}

	@Override
	@KafkaListener(topics = "henryshipping", groupId = "mygroup")
	public void consumefromTopic(String message) {
        System.out.println("we got the message and consumed this message : " + message);
		
		JSONObject jsonObject = new JSONObject(message);
        
		
		 // Extract values from the JSONObject
        //String orderId = jsonObject.getString("orderIdNew");
		
		long orderId = jsonObject.getLong("orderIdNew");
		
        String orderDate = jsonObject.getString("orderDateString");
        String products = jsonObject.getString("productsNew");
        String customerName = jsonObject.getString("customerNameNew");
        String emailId = jsonObject.getString("emailIdNew");

		//System.out.println("INSIDE consumefromTopic method : " + message);
        LOGGER.info("the topic for this consumer is {}, and message is {}", topic, message);
        
		//shipment object
		Shipment shipment = new Shipment();
		//shipment.setOrderId(Long.parseLong(orderId));
		shipment.setOrderId(orderId);
		shipment.setProducts(products);
		shipment.setCustomerName(customerName);
		shipment.setEmailId(emailId);
		shipment.setOrderDate(orderDate);
		
		LOGGER.info("shipmet object befeore storing {} and orderId is {}", shipment.getOrderId(), orderId);
		System.out.println("INSIDE consumefromTopic of shipping order is : " + orderId);
        
		//shipmentRepository.save(shipment);
		createShipment(shipment);
		
       

	}
	
	
	/* private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    */

}
