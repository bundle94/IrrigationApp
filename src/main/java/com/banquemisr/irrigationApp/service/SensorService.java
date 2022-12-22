package com.banquemisr.irrigationApp.service;

import com.banquemisr.irrigationApp.exception.SensorException;
import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface SensorService {

    // This is the pre-configured number of times the automatic irrigation system will have to retry
    // before calling the recover method.
    int CONFIGURED_RETRY_TIMES = 5;

    @Retryable(value = SensorException.class, maxAttempts = CONFIGURED_RETRY_TIMES, backoff = @Backoff(delay = 1))
    boolean triggerIrrigation(TimeSlot timeSlot) throws SensorException;;

    @Recover
    void recover(SensorException e, TimeSlot timeSlot);

    boolean isSensorOnline();
}
