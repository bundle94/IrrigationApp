package com.banquemisr.irrigationApp.service.Implementation;

import com.banquemisr.irrigationApp.exception.SensorException;
import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import com.banquemisr.irrigationApp.service.AlertService;
import com.banquemisr.irrigationApp.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorServiceImpl.class);
    private boolean status = false;
    private AlertService alertService;

    @Autowired
    public SensorServiceImpl (AlertService alertService) {
        this.alertService = alertService;
    }


    @Override
    public boolean triggerIrrigation(TimeSlot timeSlot) throws SensorException{
        LOGGER.info("triggered automatic irrigation system");
        if(!this.isSensorOnline()){
            throw new SensorException("Offline", "Could not trigger irrigation, Sensor is not online");
        }
        LOGGER.info("Activating automatic irrigation system for plot %s with %s amount of water", timeSlot.getPlotId(), timeSlot.getAmountOfWaterNeeded());
        return true;
    }

    @Override
    public void recover(SensorException e, TimeSlot timeSlot) {
        // This is a recovery method for the spring-retry implementation, it will send email alert
        // if an exception is encountered during Retry implementation
        LOGGER.info("Sending email alert to support");
        String email = "support@banquemisr.com";
        String subject = "Automatic Irrigation Failure";
        String message = String.format("Kindly investigate why the sensor is failing to trigger irrigation.");
        alertService.sendAlert(email, subject, message);
    }

    @Override
    public boolean isSensorOnline() {
        return status;
    }
}
