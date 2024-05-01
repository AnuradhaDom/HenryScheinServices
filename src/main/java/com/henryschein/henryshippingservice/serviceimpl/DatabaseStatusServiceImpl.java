package com.henryschein.henryshippingservice.serviceimpl;

import java.util.List;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.henryschein.henryshippingservice.model.DatabaseStatus;
import com.henryschein.henryshippingservice.repository.DatabaseStatusRepository;
import com.henryschein.henryshippingservice.service.DatabaseStatusService;

@Service
public class DatabaseStatusServiceImpl implements DatabaseStatusService{
	
	@Autowired
	DatabaseStatusRepository dbRepository;
	
	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ShipmentServiceImpl.class);

	@Override
	public List<DatabaseStatus> getStatus() {
		try {
			List<DatabaseStatus> dbStatus = dbRepository.findAll();
			LOGGER.info("DB Status is this{} :" , dbStatus)  ;
			return dbStatus;
		}catch(Exception e) {
			LOGGER.error("DB status not getting:", e);
			throw new RuntimeException();
		}
		
	
	}

	@Override
	public DatabaseStatus addStatus(boolean isDbUp) {
		DatabaseStatus status = new DatabaseStatus();
        status.setId(1L); // Assuming there's only one record for database status
        status.setDatabaseUp(isDbUp);
        return dbRepository.save(status);

	}

	@Override
	public DatabaseStatus updateStatus(boolean isDbUp) {
		DatabaseStatus status = dbRepository.findById(1L).orElse(null);
        if (status != null) {
            status.setDatabaseUp(isDbUp);
            return dbRepository.save(status);
        }
        return null;
    	}

}
