package com.banquemisr.irrigationApp.config;

import com.banquemisr.irrigationApp.model.entity.Plot;
import com.banquemisr.irrigationApp.model.entity.TimeSlot;
import com.banquemisr.irrigationApp.repository.PlotRepository;
import com.banquemisr.irrigationApp.repository.TimeSlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.Calendar;

@Service
public class SeedData implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeedData.class);
    private final PlotRepository plotRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public SeedData(PlotRepository plotRepository, TimeSlotRepository timeSlotRepository) {
        this.plotRepository = plotRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("==================== Seeding initial data =========================");
        Plot plot = new Plot();
        plot.setSize("1245sqm");
        plot.setGeoCordinates("12345678, 24681012");
        plot.setSurveyed(true);
        plot.setLastDateIrrigated(new Date(Instant.now().toEpochMilli()));
        plot.setCrop("Pawpaw");
        plotRepository.save(plot);
        LOGGER.info("Saved plot");

        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setPlotId(plot.getId());
        timeSlot.setAmountOfWaterNeeded(20);
        timeSlot.setTime(new Time(currentHour, currentMinute,0));
        timeSlot.setStatus("Irrigated");
        timeSlotRepository.save(timeSlot);
        LOGGER.info("Saving time slot for the above plot");

        LOGGER.info("======================== Done seeding Initial data =======================");
    }
}
