package com.henryschein.schedularservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.henryschein.henryshippingservice.service.ShipmentService;
import com.henryschein.notificationService.notificationservice.NotificationService;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;

@Component
public class ShipmentScheduler {

	@Autowired
	private NotificationService notificationService;

	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ShipmentScheduler.class);

	@Autowired
	private ShipmentService shipmentService; // Assuming you have a service to handle shipment processing

	// Map to track attempt count for each shipment
	private Map<String, Integer> attemptCountMap = new HashMap<>();

	@Scheduled(fixedRate = 20000) // Run every 5 minutes (1 * 60 * 1000 milliseconds)
	public void processShipmentFiles() {
		try {
			System.out.println("ShipmentScheduler started at: " + LocalDateTime.now());

			String folderPath = "ShipmentDataFailedToUpload"; // Folder path where shipment files are stored
			File folder = new File(folderPath);
			if (folder.exists() && folder.isDirectory()) {
				File[] files = folder.listFiles();
				if (files != null) {
					for (File file : files) {
						if (file.isFile()) {

							String shipmentFileName = file.getName(); // Assuming the file name is the shipment ID
							System.out.println("ShipmentFileName :" + shipmentFileName);
							int attemptCount = attemptCountMap.getOrDefault(shipmentFileName, 0);
							// Log attempt count
							LOGGER.info("Attempt count for file {}: {}", shipmentFileName, attemptCount);

							// Read file contents into a string
							String fileContent = readFileToString(file);

							// Process each file (upload to queue or publish to topic)
							shipmentService.publishToTopic(fileContent);

							// Once processed, delete the file
							file.delete();
							LOGGER.info("File uploaded to shipment Queue successfully: {}", file.getName());

							attemptCount++;

							// Log attempt count
							LOGGER.info("Attempt count for file {}: {}", shipmentFileName, attemptCount);

							attemptCountMap.put(shipmentFileName, attemptCount);

							if (attemptCount == 1) {
								// To email, sub and Email Body
								String toEmail = "anuradhathorat15@gmail.com";
								String subject = "File Processing Failure Notification";
								String emailBody = "Dear Support Team,\n"
										+ "This is to notify you that the following file has failed to process after 5 attempts:\n"
										+ "\n" + "File Name: [file_name]\n" + shipmentFileName + "\n"
										+ "Please take immediate action to investigate and resolve the issue.\n" + "\n";
								// Create a JSONObject and add fields
								JSONObject jsonObject = new JSONObject();

								jsonObject.put("toEmail", toEmail);
								jsonObject.put("subject", subject);
								jsonObject.put("emailBody", emailBody);

								// Convert JSONObject to JSON string
								String jsonString = jsonObject.toString();

								System.out.println(jsonString);

								notificationService.publishToTopic(jsonString);

								// Log attempt count
								LOGGER.info("Notification sent to support Team {}", jsonString);

							} else {
								LOGGER.error("Notification  not abel Send! ");
							}

						}
					}
				}
			}
		} catch (Exception e) {
			// Handle any exceptions that occur during file processing
			LOGGER.error("Error occurred while processing shipment files: {}", e.getMessage());

			e.printStackTrace();
		}

	}

	// Read File and convert to String

	private String readFileToString(File file) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
		}
		return stringBuilder.toString();
	}

}
