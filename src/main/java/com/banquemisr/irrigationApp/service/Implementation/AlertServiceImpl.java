package com.banquemisr.irrigationApp.service.Implementation;

import com.banquemisr.irrigationApp.service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {


    private static final Logger LOGGER = LoggerFactory.getLogger(AlertServiceImpl.class);

    @Override
    public void sendAlert(String email, String subject, String message) {
        LOGGER.info(String.format("Sent email alert to the support team: email=>{}, Subject=>{}, message=>{}", email,subject, message));
        //Here an actual integration will be done with an emailing library
    }
}
