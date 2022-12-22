package com.banquemisr.irrigationApp;


import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import com.banquemisr.irrigationApp.repository.TimeSlotRepository;
import com.banquemisr.irrigationApp.service.SensorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SensorServiceTest {

    @Mock
    SensorService sensor;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Test
    void triggerIrrigationWhenSensorIsNotOnline() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        var plotsByTime = timeSlotRepository.findAllByTimeBetween(new Time(currentHour,currentMinute,0), new Time(currentHour,currentMinute,59));
        assertTrue(plotsByTime.size()>0);
        Mockito.when(sensor.isSensorOnline()).thenReturn(false);
        var result  = sensor.triggerIrrigation(plotsByTime.get(0));
        verify(sensor,times(1)).triggerIrrigation(plotsByTime.get(0));
    }

    @Test
    void triggerIrrigation() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        List<TimeSlot> plotsByTime = timeSlotRepository.findAllByTimeBetween(new Time(currentHour,currentMinute,0), new Time(currentHour,currentMinute,59));
        assertTrue(plotsByTime.size()>0);
        Mockito.when(sensor.isSensorOnline()).thenReturn(true);
        Mockito.when(sensor.triggerIrrigation(plotsByTime.get(0))).thenReturn(true);
        boolean result  = sensor.triggerIrrigation(plotsByTime.get(0));
        assertTrue(result);
    }
}
