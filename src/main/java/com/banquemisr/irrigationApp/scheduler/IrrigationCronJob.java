package com.banquemisr.irrigationApp.scheduler;

import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import com.banquemisr.irrigationApp.repository.PlotRepository;
import com.banquemisr.irrigationApp.repository.TimeSlotRepository;
import com.banquemisr.irrigationApp.service.Implementation.AlertServiceImpl;
import com.banquemisr.irrigationApp.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Component
public class IrrigationCronJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(IrrigationCronJob.class);
    private final SensorService sensorService;
    private final PlotRepository plotRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public IrrigationCronJob(SensorService sensorService, PlotRepository plotRepository, TimeSlotRepository timeSlotRepository) {
        this.sensorService = sensorService;
        this.plotRepository = plotRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void automateIrrigation(){
        LOGGER.info("Automatic irrigation job started...");
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        List<TimeSlot> plots = timeSlotRepository.findAllByTimeBetween(new Time(hour,minute,0), new Time(hour,minute,59));
        for (TimeSlot slot: plots) {
            if(sensorService.triggerIrrigation(slot)){
                slot.setStatus("Irrigated");
                timeSlotRepository.save(slot);
                var plot = plotRepository.findById(slot.getPlotId());
                plot.ifPresent(value -> value.setLastDateIrrigated(new Date(Instant.now().toEpochMilli())));
            }
        }
    }
}
